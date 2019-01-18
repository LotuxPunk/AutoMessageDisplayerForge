package com.vandendaelen.automessagedisplayer;

import com.vandendaelen.automessagedisplayer.config.AMDConfig;
import com.vandendaelen.automessagedisplayer.handlers.AMDEventHandler;
import com.vandendaelen.automessagedisplayer.utils.Reference;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION.VERSION, dependencies = Reference.DEP, acceptableRemoteVersions = "*")
public class AutoMessageDisplayer {
    public static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
    }

    @Mod.EventHandler
    public void serverStarted(FMLServerStartedEvent event){
        AMDEventHandler.END_COUNTDOWN = AMDConfig.GENERAL.minutes * 60 * 20;
        logger.info(AMDEventHandler.END_COUNTDOWN);
    }

}
