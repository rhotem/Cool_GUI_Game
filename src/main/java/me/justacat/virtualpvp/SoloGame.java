package me.justacat.virtualpvp;

import me.justacat.virtualpvp.gui.GuiBuilder;
import me.justacat.virtualpvp.items.GameItem;
import me.justacat.virtualpvp.items.MoneyBag;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class SoloGame {

    private final Player player;

    public static HashMap<UUID, SoloGame> activeGames = new HashMap<>();

    private List<GameItem> gameItems = new ArrayList<>();
    private int money = 0;

    public static void startNewGame(Player player) {

        SoloGame soloGame = new SoloGame(player);

        soloGame.resetGameItems();

    }

    public SoloGame(Player player) {
        this.player = player;
        activeGames.put(player.getUniqueId(), this);
    }

    public void openGame() {
        GuiBuilder guiBuilder = new GuiBuilder(player);

        guiBuilder.setSize(36);
        guiBuilder.setTitle("Solo Game");

        int[] gameSlots = new int[]{11, 12, 13, 14, 15};

        if (gameItems.isEmpty()) {resetGameItems();}

        for (int slot : gameSlots) {


            guiBuilder.setItem(slot, gameItems.get(slot - 11).getItem());

        }

        guiBuilder.setItem(31, Material.GOLD_INGOT, 1, "&aYour Money: " + money, null, true);

        player.openInventory(guiBuilder.toInventory());

    }

    public void resetGameItems() {
        gameItems.clear();
        for (int i = 0; i < 5; i++) {
            MoneyBag moneyBag = new MoneyBag(Material.GOLD_INGOT, 1, 5);
            gameItems.add(moneyBag);
        }
    }

    public void addMoney(int amount) {money = money + amount;}

    public List<GameItem> getGameItems() {return gameItems;}

    public GameItem getGameItemBySlot(int slot) {

        int index = slot - 11;
        return getGameItemByIndex(index);

    }

    public GameItem getGameItemByIndex(int index) {return gameItems.get(index);}
}
