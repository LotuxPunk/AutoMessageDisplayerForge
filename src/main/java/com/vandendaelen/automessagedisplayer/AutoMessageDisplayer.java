package com.vandendaelen.automessagedisplayer;

import com.vandendaelen.automessagedisplayer.config.AMDConfig;
import com.vandendaelen.automessagedisplayer.utils.PlayerHelper;
import com.vandendaelen.automessagedisplayer.utils.Reference;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import org.apache.logging.log4j.Logger;

import java.util.Random;
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
        StringBuilder message = new StringBuilder();
        if (AMDConfig.GENERAL.enablePrefix) message.append("["+TextFormatting.DARK_AQUA+AMDConfig.GENERAL.prefix+TextFormatting.RESET+"] ");
        if (AMDConfig.GENERAL.random){
            Random r = new Random();
            iMsg = r.nextInt(AMDConfig.GENERAL.messages.length);
        }

        message.append(AMDConfig.GENERAL.messages[iMsg]);
        PlayerHelper.sendMessageToAllPlayer(message.toString());
        logger.info("Message displayed : "+ message.toString());

        if (!AMDConfig.GENERAL.random){
            iMsg++;
            if (iMsg >= AMDConfig.GENERAL.messages.length)
                iMsg = 0;
        }
    }

}
