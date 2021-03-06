package io.github._7isenko.scenariomix.scenarios.gameplay.apocalypse;

import io.github._7isenko.scenariomix.ScenarioMix;
import io.github._7isenko.scenariomix.scenarios.Scenario;
import io.github._7isenko.scenariomix.scenarios.config.Configuration;
import io.github._7isenko.scenariomix.utils.Border;
import io.github._7isenko.scenariomix.utils.MaterialUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.material.MaterialData;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class Apocalypse extends Scenario {
    private final Configuration<Integer> period = new Configuration<>("period", 10, "WATCH", this, "Время в тиках между", "спауном метеоров");
    private BukkitRunnable task;

    public Apocalypse() {
        super("Апокалипсис", "apocalypse", "MAGMA", "Запускает метеоритный дождь");
        addListener(new MeteorFallListener());
        addListener(new MeteorSpawner());
        addConfig(period);
    }

    @Override
    public void start() {
        updateTask();
    }

    @Override
    public void stop() {
        task.cancel();
        task = null;
    }

    public class MeteorSpawnerRunnable extends BukkitRunnable {
        private final Random random = new Random();

        @Override
        public void run() {
            if (!isStarted())
                return;
            Location location = Border.getRandomLocInBorder(Bukkit.getWorlds().get(0), random);
            location.setY(255);
            Block block = location.getBlock();
            if (block.getType() == Material.AIR) {
                Location add = block.getLocation().clone().add(0.5D, 0.0D, 0.5D);
                new Meteor(block.getWorld().spawnFallingBlock(add, new MaterialData(MaterialUtils.getMaterial("MAGMA"))));
            } else block.getWorld().createExplosion(block.getLocation(), 5);
            updateTask();
        }
    }

    private void updateTask() {
        task = new MeteorSpawnerRunnable();
        task.runTaskLater(ScenarioMix.plugin, period.getValue());
    }
}
