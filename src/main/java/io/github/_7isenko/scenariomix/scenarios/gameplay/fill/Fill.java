package io.github._7isenko.scenariomix.scenarios.gameplay.fill;

import io.github._7isenko.scenariomix.ScenarioMix;
import io.github._7isenko.scenariomix.scenarios.Configuration;
import io.github._7isenko.scenariomix.scenarios.Scenario;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Fill extends Scenario {
    public Fill() {
        super("Заполнение", "fill", Material.LAVA_BUCKET, "Постепенно заполняет мир", "выбранным блоком");
        addConfig(material);
        addConfig(delay);
        addConfig(amount);
        addConfig(current);
        addConfig(replace);
        addConfig(upside);
    }

    private final Configuration<Material> material = new Configuration<>("material", Material.LAVA, Material.BOWL, this, "Чем заполнять");
    private final Configuration<Integer> delay = new Configuration<>("delay", 60, Material.FEATHER, this, "Время между заполнениями", "(секунды)");
    private final Configuration<Integer> amount = new Configuration<>("amount", 2, Material.COBBLESTONE_STAIRS, this, "Толщина блоков, заполняемых ", "за один раз");
    private final Configuration<Integer> current = new Configuration<>("current", 2, Material.SIGN, this, "Текущая высота", "заполнения");
    private final Configuration<Boolean> replace = new Configuration<>("replace", false, Material.IRON_SPADE, this, "Заменять блоки", "любого типа");
    private final Configuration<Boolean> upside = new Configuration<>("upside", false, Material.SNOW_BALL, this, "Заполнение идёт сверху");
    private BukkitRunnable task = null;

    @Override
    public void start() {
        task = new BukkitRunnable() {
            @Override
            public void run() {
                if (!isStarted()) {
                    task.cancel();
                    return;
                }
                World world;
                try {
                    world = ((Player) Bukkit.getOnlinePlayers().toArray()[0]).getWorld();
                } catch (IndexOutOfBoundsException e) {
                    Bukkit.broadcastMessage("никого нет...");
                    return;
                }
                int borderSize = (int) Math.ceil(world.getWorldBorder().getSize());
                if (borderSize > 5000) {
                    Bukkit.broadcast(ChatColor.RED + "Размер границ мира больше 5000, заполнение отменено. Нужен перезапуск", "op");
                    task.cancel();
                    task = null;
                    return;
                }
                fill(world, borderSize);
                start();
            }
        };
        task.runTaskLater(ScenarioMix.plugin, delay.value() * 20);
    }

    @Override
    public void stop() {
        if (task != null && !task.isCancelled()) {
            task.cancel();
            task = null;
        }

    }

    private void runIt() {

    }

    private void fill(World world, int size) {
        if (current.value() >= 256)
            return;
        Location center = world.getWorldBorder().getCenter();
        for (int y = 0; y < amount.value(); y++) {
            for (int x = center.getBlockX() - size / 2; x <= center.getBlockX() + size / 2; x++) {
                for (int z = center.getBlockZ() - size / 2; z <= center.getBlockZ() + size / 2; z++) {
                    Block block = world.getBlockAt(x, current.value() + y, z);
                    if (!replace.value() && block.getType() != Material.AIR)
                        continue;
                    block.setType(material.value(), false);
                }
            }
        }
        if (upside.getValue()) {
            current.setValue(current.getValue() - amount.value());
        }
        current.setValue(current.getValue() + amount.value());
    }
}