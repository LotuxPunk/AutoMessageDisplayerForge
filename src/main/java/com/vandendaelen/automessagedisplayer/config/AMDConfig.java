package com.vandendaelen.automessagedisplayer.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.List;

public class AMDConfig{
    public static final AMDConfig CONFIG;
    public static final ForgeConfigSpec CONFIG_SPEC;
    
    public final ForgeConfigSpec.IntValue timer;
    public final ForgeConfigSpec.ConfigValue<List<String>> messages;
    public final ForgeConfigSpec.ConfigValue<String> prefix;
    public final ForgeConfigSpec.BooleanValue enablePrefix;
    public final ForgeConfigSpec.BooleanValue updateChecker;
    public final ForgeConfigSpec.BooleanValue random;
    public final ForgeConfigSpec.IntValue minPlayerOnline;

    
    static {
        Pair<AMDConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(AMDConfig::new);
        CONFIG_SPEC = specPair.getRight();
        CONFIG = specPair.getLeft();
    }

    public AMDConfig(ForgeConfigSpec.Builder builder) {
        builder.push("general");
        timer = builder
                .comment("Minutes between 2 messages")
                .translation("amd.config.minutes")
                .defineInRange("timer",30, 1, Integer.MAX_VALUE);
        messages = builder
                .comment("Messages to displays")
                .translation("amd.config.messages")
                .define("messages", Arrays.asList("Thanks for downloading AutoMessageDisplayer", "Cake or pie ?"));
                //.defineList("messages", Arrays.asList("Thanks for downloading AutoMessageDisplayer", "Cake or pie ?"), null);
        prefix = builder
                .comment("Prefix of the message")
                .translation("amd.config.prefix")
                .define("prefix","AutoMessageDisplayer");
        enablePrefix = builder
                .comment("Enable prefix before the message ?")
                .translation("amd.config.enable.prefix")
                .define("enable_prefix", true);
        updateChecker = builder
                .comment("Check if an update exist ?")
                .translation("amd.config.update")
                .define("update_checker", true);
        random = builder
                .comment("Display messages randomly")
                .translation("amd.config.random")
                .define("random",false);
        minPlayerOnline = builder
                .comment("Display messages only when there are at least X players online")
                .defineInRange("minPlayerOnline",0, 0, 9999);
        builder.pop();
    }

    public static int getTimer() {
        return CONFIG.timer.get();
    }

    public static List<String> getMessages() {
        return CONFIG.messages.get();
    }

    public static String getPrefix() {
        return CONFIG.prefix.get();
    }

    public static boolean getEnablePrefix() {
        return CONFIG.enablePrefix.get();
    }

    public static boolean getUpdateChecker() {
        return CONFIG.updateChecker.get();
    }

    public static boolean getRandom() {
        return CONFIG.random.get();
    }

    public static int getMinPlayerRequired(){
        return  CONFIG.minPlayerOnline.get();
    }
}
