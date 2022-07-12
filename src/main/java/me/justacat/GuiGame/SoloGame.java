package me.justacat.GuiGame;

import me.justacat.GuiGame.gui.GuiBuilder;
import me.justacat.GuiGame.items.GameItem;
import me.justacat.GuiGame.misc.Chat;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class SoloGame {

    private final Player player;

    public static HashMap<UUID, SoloGame> activeGames = new HashMap<>();

    private List<GameItem> gameItems = new ArrayList<>();
    private HashMap<Integer, ItemStack> displays = new HashMap<>();
    private int money = 0;
    private int moneyPerSecond = 0;

    private int slotsUnlocked = 1;

    public static void startNewGame(Player player) {

        SoloGame soloGame = new SoloGame(player);

        soloGame.resetGameItems();

    }

    public SoloGame(Player player) {
        this.player = player;
        activeGames.put(player.getUniqueId(), this);

        new BukkitRunnable() {
            @Override
            public void run() {

                for (int i = 1; i <= 5; i++) {

                    if (displays.containsKey(i) && displays.get(i).getItemMeta().getLocalizedName().contains("_seconds")) {


                        int secondsLeft = Integer.parseInt(displays.get(i).getItemMeta().getLocalizedName().replace("_seconds", ""));

                        secondsLeft = secondsLeft - 1;

                        if (secondsLeft > 0) {
                            ItemStack itemStack = displays.get(i);
                            ItemMeta itemMeta = itemStack.getItemMeta();

                            itemMeta.setLocalizedName(secondsLeft + "_seconds");

                            itemMeta.setDisplayName(Chat.colorMessage("&7New item in &b" + secondsLeft + "&7 seconds!"));

                            itemStack.setItemMeta(itemMeta);

                            displays.put(i, itemStack);

                        } else {
                            displays.remove(i);
                        }

                    }
                }

                if (moneyPerSecond > 0) {
                    money = money + moneyPerSecond;
                }

                if (player.getOpenInventory().getTitle().equals("Solo Game")) {
                        openGame();
                }



            }

        }.runTaskTimer(GuiGame.instance, 20, 20);
    }

    public void openGame() {
        GuiBuilder guiBuilder = new GuiBuilder(player);

        guiBuilder.setSize(36);
        guiBuilder.setTitle("Solo Game");

        int[] gameSlots = new int[]{11, 12, 13, 14, 15};

        if (gameItems.isEmpty()) {resetGameItems();}

        for (int slot : gameSlots) {

                if (displays.containsKey(slot - 10)) {
                    guiBuilder.setItem(slot, displays.get(slot - 10));
                } else {
                    guiBuilder.setItem(slot, getSlotItem(slot));
                }



        }

        guiBuilder.setItem(31, Material.GOLD_INGOT, 1, "&aYour Money: &6" + money, null, true);

        guiBuilder.setItem(30, Material.GOLD_BLOCK, 1, "&aMoney Per Second: &6" + moneyPerSecond, null, true);

        guiBuilder.setEmpty(Material.GLASS_PANE, 1, "&0", null, true);

        player.openInventory(guiBuilder.toInventory());

    }

    public void resetGameItems() {
        gameItems.clear();
        for (int i = 0; i < slotsUnlocked; i++) {


            GameItem gameItem = GameItem.randomGameItem();
            gameItems.add(gameItem);


        }
    }

    public void resetSlot(int slotNumber) {

        gameItems.add(slotNumber - 1, GameItem.randomGameItem());
        gameItems.remove(slotNumber);

        ItemStack item = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);

        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.setLocalizedName(6 - slotNumber + "_seconds");

        itemMeta.setDisplayName(Chat.colorMessage("&7New item in &b" + (6 - slotNumber) + "&7 seconds!"));

        item.setItemMeta(itemMeta);

        displays.put(slotNumber, item);


    }

    public ItemStack getSlotItem(int slot) {
        if (slotsUnlocked >= slot - 10) {
            return gameItems.get(slot - 11).getItem();
        } else {
            ItemStack item = new ItemStack(Material.RED_STAINED_GLASS_PANE);

            ItemMeta itemMeta = item.getItemMeta();

            itemMeta.setDisplayName(Chat.colorMessage("&cLocked Slot!"));

            int price = (int) (Math.pow(slot - 10, 3) * 1000);

            if (slotsUnlocked == slot - 11) {
                itemMeta.setLore(Chat.colorList("&0", "&7Click here to unlock for " + price + " &7coins!"));
                itemMeta.setLocalizedName("lockedSlot:" + price);
            } else {
                itemMeta.setLore(Chat.colorList("&0", "&7Buy the previous slot before unlocking this one!"));
                itemMeta.setLocalizedName("lockedSlot!");
            }


            item.setItemMeta(itemMeta);

            return item;
        }
    }

    public void addMoney(int amount) {money = money + amount;}

    public int getMoney() {return money;}
    public void unlockSlot() {slotsUnlocked++;}

    public void addMoneyPerSecond(int amount) {moneyPerSecond = moneyPerSecond + amount;}
    public List<GameItem> getGameItems() {return gameItems;}

    public GameItem getGameItemBySlot(int slot) {

        int index = slot - 11;
        return getGameItemByIndex(index);

    }

    public GameItem getGameItemByIndex(int index) {return gameItems.get(index);}
}
