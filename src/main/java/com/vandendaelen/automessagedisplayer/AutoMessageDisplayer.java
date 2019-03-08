package com.vandendaelen.automessagedisplayer;

import com.vandendaelen.automessagedisplayer.utils.Reference;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Reference.MOD_ID)
public class AutoMessageDisplayer {
    public static final Logger LOGGER = LogManager.getLogger();

    public AutoMessageDisplayer() {

        MinecraftForge.EVENT_BUS.register(this);
    }

    //    @Mod.EventHandler
//    public void preInit(FMLPreInitializationEvent event)
//    {
//        logger = event.getModLog();
//    }
//
//    @Mod.EventHandler
//    public void serverStarted(FMLServerStartedEvent event){
//        AMDEventHandler.END_COUNTDOWN = AMDConfig.GENERAL.minutes * 60 * 20;
//        logger.info(AMDEventHandler.END_COUNTDOWN);
//    }

}
