package me.justacat.guigame.listeners;

import me.justacat.guigame.SoloGame;
import me.justacat.guigame.items.GameItem;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

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

            ItemStack item = e.getCurrentItem();

            if (item.getItemMeta().getLocalizedName().equals("GameItem")) {
                GameItem gameItem = soloGame.getGameItemBySlot(e.getRawSlot());

                gameItem.onClick(player, slot - 10);
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5F, 0.7F);
                soloGame.resetSlot(slot - 10);
                soloGame.openGame();
            } else if (item.getItemMeta().getLocalizedName().contains("lockedSlot:")) {
                int price = Integer.parseInt(item.getItemMeta().getLocalizedName().replace("lockedSlot:", ""));

                if (price <= soloGame.getMoney()) {

                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5F, 0);
                    soloGame.addMoney(-price);
                    soloGame.unlockSlot();
                    soloGame.resetGameItems();
                    soloGame.openGame();


                } else {
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 0.5F, 1F);
                }

            } else if (item.getType() == Material.RED_STAINED_GLASS_PANE || item.getType() == Material.GRAY_STAINED_GLASS_PANE)
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 0.5F, 1F);
            }
    }

}






