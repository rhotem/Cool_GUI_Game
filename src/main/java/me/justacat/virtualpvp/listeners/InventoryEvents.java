package me.justacat.virtualpvp.listeners;

import me.justacat.virtualpvp.SoloGame;
import me.justacat.virtualpvp.items.GameItem;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryEvents implements Listener {


    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {


        if (e.getView().getTitle().equals("Solo Game")) {

            Player player = (Player) e.getWhoClicked();

            SoloGame soloGame = SoloGame.activeGames.get(player.getUniqueId());

            GameItem gameItem = soloGame.getGameItemBySlot(e.getRawSlot());

            gameItem.onClick(player);
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.5F, 0.5F);
            soloGame.resetGameItems();
            soloGame.openGame();


        }

    }

}



