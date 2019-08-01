package com.vandendaelen.automessagedisplayer.handlers;

import com.vandendaelen.automessagedisplayer.AutoMessageDisplayer;
import com.vandendaelen.automessagedisplayer.config.AMDConfig;
import com.vandendaelen.automessagedisplayer.utils.PlayerHelper;
import com.vandendaelen.automessagedisplayer.utils.Reference;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

import java.util.Random;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class AMDEventHandler {
    public static int countdown = 0;
    public static int iMsg = 0;

    public static void messageDisplayer() {
        String message = null;

        //Prefix
        StringBuilder text = new StringBuilder();
        if (AMDConfig.getEnablePrefix())
            text.append("[").append(TextFormatting.DARK_AQUA).append(AMDConfig.getPrefix()).append(TextFormatting.RESET).append("] ");

        if (AMDConfig.getRandom()) {
            Random r = new Random();
            iMsg = r.nextInt(AMDConfig.getMessages().size());
        } else {
            if (iMsg < AMDConfig.getMessages().size()) {
                message = AMDConfig.getMessage(iMsg);
            }

            iMsg++;
            if (iMsg >= AMDConfig.getMessages().size())
                iMsg = 0;
        }

        text.append(message);
        PlayerHelper.sendMessageToAllPlayer(text.toString());
        AutoMessageDisplayer.LOGGER.info("Message displayed : " + text.toString());
    }

    @SubscribeEvent
    public static void onServerUpdate(TickEvent.ServerTickEvent event){
        if (event.phase == TickEvent.Phase.START && ServerLifecycleHooks.getCurrentServer().getCurrentPlayerCount() >= AMDConfig.getMinPlayerRequired()){
            countdown++;
            if (countdown >= AMDConfig.getTimer()){
                messageDisplayer();
                countdown = 0;
            }
        }
    }

    @SubscribeEvent
    public static void serverStarted(FMLServerStartedEvent event){
        AutoMessageDisplayer.LOGGER.info(AMDConfig.getTimer());
    }
}
