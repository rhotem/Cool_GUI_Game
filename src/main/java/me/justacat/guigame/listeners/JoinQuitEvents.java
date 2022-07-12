package me.justacat.guigame.listeners;

import me.justacat.guigame.SoloGame;
import me.justacat.guigame.misc.FileManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuitEvents implements Listener {


    @EventHandler
    public void onQuit(PlayerQuitEvent e) {

        Player player = e.getPlayer();

        FileManager.savePlayerData(player); //saves the player's game
        SoloGame.activeGames.remove(player.getUniqueId()); //removes the player's game from the map


    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        Player player = e.getPlayer();

        FileManager.loadPlayerData(player); //loads the player's game

    }



}
