package com.vandendaelen.automessagedisplayer;

import com.vandendaelen.automessagedisplayer.config.AMDConfig;
import com.vandendaelen.automessagedisplayer.utils.PlayerHelper;
import com.vandendaelen.automessagedisplayer.utils.Reference;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION.VERSION, dependencies = Reference.DEP, acceptableRemoteVersions = "*")
public class AutoMessageDisplayer {
    private static Logger logger;
    public static final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    public static int iMsg = 0;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
    }

    @Mod.EventHandler
    public void serverStarted(FMLServerStartedEvent event){
        executorService.scheduleAtFixedRate(AutoMessageDisplayer::messageDisplayer, AMDConfig.GENERAL.minutes,AMDConfig.GENERAL.minutes, TimeUnit.MINUTES);
    }

    private static void messageDisplayer(){
        PlayerHelper.sendMessageToAllPlayer(AMDConfig.GENERAL.messages[iMsg]);
        logger.info("Message displayed : "+AMDConfig.GENERAL.messages[iMsg]);
        iMsg++;
        if (iMsg >= AMDConfig.GENERAL.messages.length)
            iMsg = 0;
    }

}
