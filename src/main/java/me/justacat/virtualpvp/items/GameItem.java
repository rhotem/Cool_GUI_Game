package me.justacat.virtualpvp.items;

import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public abstract class GameItem {

    protected ItemStack item;

    public GameItem(Material type, int amount) {
        item = new ItemStack(type, amount);
    }

    public GameItem(Material type, int amount, String... lore) {
        ItemStack itemStack = new ItemStack(type, amount);

        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setLore(Arrays.asList(lore));

        itemMeta.setLocalizedName("GameItem");

        itemMeta.addItemFlags(ItemFlag.values());


        itemStack.setItemMeta(itemMeta);
        item = itemStack;
    }

    public ItemStack getItem() {return item;}

    public abstract void onClick(Player player);

}
