package com.vandendaelen.automessagedisplayer.utils;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

import java.util.List;

public class PlayerHelper {
    public static void sendMessage(ServerPlayerEntity player, String message){
        player.sendMessage(new StringTextComponent(message));
    }

    public static void sendMessageToAllPlayer(String message){
        List<ServerPlayerEntity> players = ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayers();
        for(ServerPlayerEntity playerMP : players){
            sendMessage(playerMP, message);
        }
    }
}
