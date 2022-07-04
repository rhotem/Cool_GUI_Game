package me.justacat.virtualpvp.items;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class MoneyBag extends GameItem {


    public MoneyBag(Material type, int amount, int moneyGives) {
        super(type, amount, "&0", "&7Gives &6" + moneyGives + "coins!");
    }


    @Override
    public void onClick(Player player) {
        //give money
    }
}
