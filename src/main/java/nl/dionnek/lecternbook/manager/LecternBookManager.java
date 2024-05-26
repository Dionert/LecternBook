package nl.dionnek.lecternbook.manager;

import nl.dionnek.lecternbook.utils.LecternBookUtils;
import nl.dionnek.lecternbook.utils.MessageUtils;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Lectern;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class LecternBookManager {
    public boolean isLectern(Block block) {
        return block.getType() == Material.LECTERN;
    }

    public void readBookToPlayer(Player player, ItemStack book, int pageNumber) {
        List<String> pages = LecternBookUtils.getBookPages(book);

        if (pages.isEmpty()) {
            MessageUtils.sendMessage(player, MessageUtils.formatMessage("&e[LB] &fThere are no pages to read."));
            return;
        }

        if (pageNumber < 1 || pageNumber > pages.size()) {
            MessageUtils.sendMessage(player, MessageUtils.formatMessage("&e[LB] &fThis page does not exist."));
            return;
        }

        String pageContent = pages.get(pageNumber - 1);
        MessageUtils.sendMessage(player, MessageUtils.formatMessage("&e[LB] &f[Page " + pageNumber + "/" + pages.size() + "]"));
        MessageUtils.sendMessage(player, pageContent);
    }

    public ItemStack getBookFromLectern(Block block) {
        if (isLectern(block)) {
            Lectern lectern = (Lectern) block.getState();
            Inventory inventory = lectern.getInventory();
            ItemStack book = inventory.getItem(0);

            if (book != null && book.getType() == Material.WRITTEN_BOOK) {
                return book;
            }
        }
        return null;
    }
}