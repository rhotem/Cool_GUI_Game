package me.justacat.virtualpvp.items;

import me.justacat.virtualpvp.SoloGame;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class MoneyBag extends GameItem {

    private final int moneyGives;

    public MoneyBag(Material type, int amount, int moneyGives) {
        super(type, amount, "&6Money Bag", "&0", "&7Gives &6" + moneyGives + "coins!");
        this.moneyGives = moneyGives;
    }


    @Override
    public void onClick(Player player) {
        SoloGame soloGame = SoloGame.activeGames.get(player.getUniqueId());
        soloGame.addMoney(moneyGives);
    }
}
