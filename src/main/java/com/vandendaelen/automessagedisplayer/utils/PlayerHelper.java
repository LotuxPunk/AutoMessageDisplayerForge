package com.vandendaelen.automessagedisplayer.utils;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.FMLCommonHandler;

import java.util.List;

public class PlayerHelper {
    public static void sendMessage(EntityPlayerMP player, String message){
        player.sendMessage(new TextComponentString(message));
    }

    public static void sendMessageToAllPlayer(String message){
        List<EntityPlayerMP> players = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayers();
        for(EntityPlayerMP playerMP : players){
            sendMessage(playerMP, message);
        }
    }
}
