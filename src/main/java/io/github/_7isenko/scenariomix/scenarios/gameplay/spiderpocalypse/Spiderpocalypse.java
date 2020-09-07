package io.github._7isenko.scenariomix.scenarios.gameplay.spiderpocalypse;

import io.github._7isenko.scenariomix.ScenarioMix;
import io.github._7isenko.scenariomix.scenarios.Scenario;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.material.MaterialData;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.util.*;
import java.util.stream.Collectors;

public class Spiderpocalypse extends Scenario {
    protected Random random;
    protected Set<Chunk> chunks;
    protected Set<Chunk> loaded;
    protected Material type;
    protected BukkitTask task;
    protected World world;

    public Spiderpocalypse() {
        super("Паукопакалипсис", "spiderpocalypse", Material.SPIDER_EYE, "Дождь из пауков", "аллилуйя");
    }

    @Override
    public void start() {
        addListener(new SpiderFallListener());
        addListener(new SpiderAttackListener());
        addBukkitRunnable(new ScarySpiderRunnable(), 20);
        random = new Random();
        chunks = new HashSet<>();
        loaded = new HashSet<>();
        type = Material.SNOW_BLOCK;
        world = Bukkit.getWorlds().get(0);
        task = (new BukkitRunnable() {
            public void run() {
                for (Chunk chunk : world.getLoadedChunks()) {
                    if (loaded.contains(chunk)) continue;
                    startChunk(chunk);
                }
            }
        }).runTaskTimer(ScenarioMix.plugin, 0L, 200L);
    }

    @Override
    public void stop() {
        if (task != null) {
            loaded.clear();
            task.cancel();
            task = null;
        }
    }

    private void startChunk(final Chunk chunk) {
        loaded.add(chunk);
        new BukkitRunnable() {
            int counter = 0;
            final int tickMiss = 200;

            public void run() {
                if (!isStarted()) {
                    cancel();
                } else if (!chunk.isLoaded()) {
                    loaded.remove(chunk);
                    cancel();
                } else {
                    if (counter % tickMiss == 0 && random.nextFloat() > 0.50) {
                        Block block = chunk.getBlock(getRandomNumberBetween(0, 15), getRandomNumberBetween(10, 180), getRandomNumberBetween(0, 15));
                        if (block.getType() == Material.AIR) {
                            Location location = block.getLocation().clone().add(0.5D, 0.0D, 0.5D);
                            Entity spider;
                            if (random.nextFloat() > 0.90f)
                                spider = block.getWorld().spawnEntity(location, EntityType.CAVE_SPIDER);
                            else
                                spider = block.getWorld().spawnEntity(location, EntityType.SPIDER);
                            spider.setVelocity(getVectorToNearestPlayer(spider));
                        }
                        chunks.add(chunk);
                    }
                    ++counter;
                }
            }
        }.runTaskTimer(ScenarioMix.plugin, 0L, 1L);

    }

    private int getRandomNumberBetween(int min, int max) {
        return min == max ? min : random.nextInt(max - min + 1) + min;
    }

    private Vector getVectorToNearestPlayer(Entity entity) {
        List<Entity> players = entity.getNearbyEntities(100, 100, 100).stream().filter(entity1 -> entity1.getType() == EntityType.PLAYER).collect(Collectors.toList());
        if (!players.isEmpty()) {
            return players.get(0).getLocation().toVector().subtract(entity.getLocation().add(0, 1, 0).toVector()).normalize().multiply(4);
        } else return new Vector(random.nextDouble() - 0.5, -0.5, random.nextDouble() - 0.5);
    }
}