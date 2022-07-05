package me.justacat.virtualpvp.items;

import me.justacat.virtualpvp.misc.Chat;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public abstract class GameItem {

    protected ItemStack item;

    public GameItem(Material type, int amount,String name, String... lore) {

        ItemStack itemStack = new ItemStack(type, amount);

        ItemMeta itemMeta = itemStack.getItemMeta();

        List<String> newLore = new ArrayList<>();
        for (String line : lore) {
            newLore.add(Chat.colorMessage(line));
        }

        itemMeta.setLore(newLore);

        itemMeta.setLocalizedName("GameItem");

        itemMeta.addItemFlags(ItemFlag.values());

        itemMeta.setDisplayName(Chat.colorMessage(name));

        itemStack.setItemMeta(itemMeta);
        item = itemStack;
    }

    public ItemStack getItem() {return item;}

    public abstract void onClick(Player player);

    public static GameItem randomGameItem() {

        double random = Math.random();

        if (random > 0.9) {
            return MoneyGenerator.randomMoneyGenerator();
        } else {
            return MoneyBag.randomMoneyBag();
        }

    }

}
