package nl.dionnek.lecternbook.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.ArrayList;
import java.util.List;

public class LecternBookUtils {

    public static boolean isBook(ItemStack item) {
        return item != null && item.getType() == Material.WRITTEN_BOOK;
    }


    public static List<String> getBookPages(ItemStack book) {
        if (!isBook(book)) {
            return null;
        }

        BookMeta bookMeta = (BookMeta) book.getItemMeta();
        if (bookMeta == null) {
            return null;
        }

        return new ArrayList<>(bookMeta.getPages());
    }
}