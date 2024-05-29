package nl.dionnek.lecternbook.gui;

import nl.dionnek.lecternbook.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class LecternBookGUI {

    private final Player player;
    private final ItemStack book;

    public LecternBookGUI(Player player, ItemStack book) {
        this.player = player;
        this.book = book;
    }

    public void openBookGui() {
        Inventory gui = Bukkit.createInventory(null, 27, "Lectern Book");
        List<String> lore = new ArrayList<>();


        ItemStack bookCopy = book.clone();
        ItemMeta meta = bookCopy.getItemMeta();

        if (meta instanceof BookMeta) {
            BookMeta bookMeta = (BookMeta) meta;

            lore.add(" ");
            lore.add(MessageUtils.formatMessage("&8------------------------"));
            lore.add(MessageUtils.formatMessage("&6Title: &f" + bookMeta.getTitle()));
            lore.add(MessageUtils.formatMessage("&6Pages: &f" + bookMeta.getPageCount()));
            lore.add(MessageUtils.formatMessage("&8------------------------"));

            bookMeta.setAuthor(null);

            bookMeta.setLore(lore);
            bookCopy.setItemMeta(bookMeta);
        }

        gui.setItem(13, bookCopy);
        player.openInventory(gui);
    }
}
