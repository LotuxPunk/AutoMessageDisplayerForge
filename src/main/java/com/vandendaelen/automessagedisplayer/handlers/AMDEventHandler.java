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

import java.util.Random;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class AMDEventHandler {
    public static int countdown = 0;
    public static int END_COUNTDOWN = 0;
    public static int iMsg = 0;

    public static void messageDisplayer(){
        StringBuilder message = new StringBuilder();
        if (AMDConfig.getEnablePrefix()) message.append("["+ TextFormatting.DARK_AQUA+AMDConfig.getPrefix()+TextFormatting.RESET+"] ");
        if (AMDConfig.getRandom()){
            Random r = new Random();
            iMsg = r.nextInt(AMDConfig.getMessages().size());
        }

        message.append(AMDConfig.getMessages().toArray()[iMsg]);
        PlayerHelper.sendMessageToAllPlayer(message.toString());
        AutoMessageDisplayer.LOGGER.info("Message displayed : "+ message.toString());

        if (!AMDConfig.getRandom()){
            iMsg++;
            if (iMsg >= AMDConfig.getMessages().size())
                iMsg = 0;
        }
    }

    @SubscribeEvent
    public static void onServerUpdate(TickEvent.ServerTickEvent event){
        if (event.phase == TickEvent.Phase.START ){
            countdown++;
            if (countdown >= END_COUNTDOWN){
                messageDisplayer();
                countdown = 0;
            }
        }
    }

    @SubscribeEvent
    public static void serverStarted(FMLServerStartedEvent event){
        AMDEventHandler.END_COUNTDOWN = AMDConfig.getTimer() * 60 * 20;
        AutoMessageDisplayer.LOGGER.info(AMDEventHandler.END_COUNTDOWN);
    }
}
