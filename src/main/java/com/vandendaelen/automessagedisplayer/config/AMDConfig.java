package com.vandendaelen.automessagedisplayer.config;

import com.vandendaelen.automessagedisplayer.utils.Reference;
import net.minecraftforge.common.config.Config;

@Config(modid = Reference.MOD_ID, name = Reference.MOD_NAME)
public class AMDConfig {
    public static final General GENERAL = new General();

    public static class General{
        @Config.LangKey("amd.config.minutes")
        @Config.Comment("Minutes between 2 messages")
        public int minutes = 30;

        @Config.LangKey("amd.config.messages")
        @Config.Comment("Messages to displays")
        public String[] messages = {"Thanks for downloading AutoMessageDisplayer", "Cake or pie ?"};

        @Config.LangKey("amd.config.update")
        @Config.Comment("Check if an update exist ?")
        public boolean updateChecker = true;

        @Config.LangKey("amd.config.random")
        @Config.Comment("Display messages randomly")
        public boolean random = false;
    }
}
