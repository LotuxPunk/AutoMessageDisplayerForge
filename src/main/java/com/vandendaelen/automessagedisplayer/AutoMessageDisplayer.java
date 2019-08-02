package com.vandendaelen.automessagedisplayer;

import com.vandendaelen.automessagedisplayer.config.AMDConfig;
import com.vandendaelen.automessagedisplayer.utils.Reference;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Reference.MOD_ID)
public class AutoMessageDisplayer {
    public static final Logger LOGGER = LogManager.getLogger();
    public static AutoMessageDisplayer INSTANCE;

    public AutoMessageDisplayer() {
        INSTANCE = this;
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, AMDConfig.COMMON_SPEC);
        MinecraftForge.EVENT_BUS.register(this);
    }
}
