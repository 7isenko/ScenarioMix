package io.github._7isenko.scenariomix.utils;

import io.github._7isenko.scenariomix.ScenarioMix;
import io.github._7isenko.scenariomix.scenarios.config.Configuration;
import io.github._7isenko.scenariomix.scenarios.config.ValueType;

public class Parser {
    public static Boolean parseBoolean(String string) throws IllegalArgumentException {
        if (string.equalsIgnoreCase("true") || string.equalsIgnoreCase("allow"))
            return true;
        if (string.equalsIgnoreCase("false") || string.equalsIgnoreCase("falce") || string.equalsIgnoreCase("none") || string.equalsIgnoreCase("deny"))
            return false;
        throw new IllegalArgumentException("Ничего не понял");
    }

    @SuppressWarnings("rawtypes")
    public static String getConfigCommand(Configuration configuration) {
        return "/" + ScenarioMix.command + " " + configuration.getScenario().getConfigName() + " " + configuration.getName() + " <" + createTypeMessage(configuration) + ">";
    }

    @SuppressWarnings("rawtypes")
    public static String createTypeMessage(Configuration configuration) {
        if (configuration.getValueType() == ValueType.INTEGER)
            return "целое число";
        if (configuration.getValueType() == ValueType.BOOLEAN)
            return "true/false"; // или allow/deny
        if (configuration.getValueType() == ValueType.MATERIAL)
            return "тип";
        if (configuration.getValueType() == ValueType.STRING)
            return "текст";
        return "значение";
    }
}
