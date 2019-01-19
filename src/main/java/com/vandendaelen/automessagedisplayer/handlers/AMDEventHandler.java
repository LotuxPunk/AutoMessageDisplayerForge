package com.vandendaelen.automessagedisplayer.handlers;

import com.vandendaelen.automessagedisplayer.AutoMessageDisplayer;
import com.vandendaelen.automessagedisplayer.config.AMDConfig;
import com.vandendaelen.automessagedisplayer.utils.PlayerHelper;
import com.vandendaelen.automessagedisplayer.utils.Reference;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import scala.collection.immutable.List;

import java.util.Arrays;
import java.util.Random;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class AMDEventHandler {
    public static int countdown = 0;
    public static int END_COUNTDOWN = 0;
    public static int iMsg = 0;

    public static void messageDisplayer(){
        StringBuilder message = new StringBuilder();
        if (AMDConfig.GENERAL.enablePrefix) message.append("["+ TextFormatting.DARK_AQUA+AMDConfig.GENERAL.prefix+TextFormatting.RESET+"] ");
        if (AMDConfig.GENERAL.random){
            Random r = new Random();
            iMsg = r.nextInt(AMDConfig.GENERAL.messages.length);
        }

        message.append(AMDConfig.GENERAL.messages[iMsg]);
        PlayerHelper.sendMessageToAllPlayer(message.toString());
        AutoMessageDisplayer.logger.info("Message displayed : "+ message.toString());

        if (!AMDConfig.GENERAL.random){
            iMsg++;
            if (iMsg >= AMDConfig.GENERAL.messages.length)
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
    public static void onPlayerConnect(PlayerEvent.PlayerLoggedInEvent event){
        if (AMDConfig.GENERAL.updateChecker){
            String[] admins = event.player.getServer().getPlayerList().getOppedPlayerNames();
            if (Arrays.asList(admins).contains(event.player.getName())){
                ForgeVersion.CheckResult version = ForgeVersion.getResult(Loader.instance().activeModContainer());
                if (version.status == ForgeVersion.Status.OUTDATED){
                    TextComponentString url = new TextComponentString(TextFormatting.DARK_AQUA + TextFormatting.BOLD.toString() + "New update available !");
                    url.getStyle().setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, Reference.updateURL));
                    url.getStyle().setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponentString("Open link")));
                    event.player.sendMessage(new TextComponentString(TextFormatting.DARK_RED + "["+Reference.MOD_NAME+"] : ").appendSibling(url));
                }
            }
        }
    }
}
