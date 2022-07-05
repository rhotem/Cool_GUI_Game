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

            int slot = e.getRawSlot();

            if (slot > 35) return;
            if (slot < 0) return;

            e.setCancelled(true);

            Player player = (Player) e.getWhoClicked();

            SoloGame soloGame = SoloGame.activeGames.get(player.getUniqueId());


            if (e.getCurrentItem().getItemMeta().getLocalizedName().equals("GameItem")) {
                GameItem gameItem = soloGame.getGameItemBySlot(e.getRawSlot());

                gameItem.onClick(player);
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5F, 0.7F);
                soloGame.resetGameItems();
                soloGame.openGame();
            }



        }

    }

}



