package io.github._7isenko.scenariomix.scenarios.tools.heightlimit;

import io.github._7isenko.scenariomix.scenarios.config.Configuration;
import io.github._7isenko.scenariomix.scenarios.Scenario;
import org.bukkit.Material;

public class HeightLimit extends Scenario {
    public HeightLimit() {
        super("Ограничение высоты", "height_limit", "BEDROCK", "Устанавливает максимальную", "и минимальную высоту", "установки блоков");
        addConfig(max);
        addConfig(min);
        addConfig(ignoreCreative);
        addConfig(allowBuild);
        addConfig(allowBreak);
        addListener(new BlockEventsListener(this));
    }

    private final Configuration<Integer> max = new Configuration<>("max", 126, "GLASS", this, "Максимальная высота");
    private final Configuration<Integer> min = new Configuration<>("min", 0, "STONE", this, "Минимальная высота");
    private final Configuration<Boolean> ignoreCreative = new Configuration<>("ignore_creative", true, "YELLOW_GLAZED_TERRACOTTA", this, "Игроки в креативе", "обходят ограничения");
    private final Configuration<Boolean> allowBuild = new Configuration<>("allow_build", false, "WOOD_BUTTON", this, "Разрешает строить", "на любой высоте");
    private final Configuration<Boolean> allowBreak = new Configuration<>("allow_break", false, "IRON_PICKAXE", this, "Разрешает ломать", "на любой высоте");

    @Override
    public void start() {
    }

    @Override
    public void stop() {

    }

    public int getMax() {
        return max.value();
    }

    public int getMin() {
        return min.value();
    }

    public boolean isIgnoreCreative() {
        return ignoreCreative.value();
    }

    public boolean isBuildAllowed() {
        return allowBuild.value();
    }

    public boolean isBreakAllowed() {
        return allowBreak.value();
    }
}
