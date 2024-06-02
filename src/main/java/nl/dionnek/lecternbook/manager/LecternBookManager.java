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



    /**
     * Checks if the block is a lectern.
     *
     * @param block The block to check.
     */
    public boolean isLectern(Block block) {
        return block.getType() == Material.LECTERN;
    }



    /**
     * Reads a specific page of a book to the given player.
     *
     * @param player     The player to whom the book should be read.
     * @param book       The ItemStack representing the book.
     * @param pageNumber The page number to read.
     */
    public void readBookToPlayer(Player player, ItemStack book, int pageNumber) {
        List<String> pages = LecternBookUtils.getBookPages(book);

        if (pages == null || pages.isEmpty()) {
            MessageUtils.sendMessage(player, MessageUtils.formatMessage("&e[LB] &fThere are no pages to read."));
            return;
        }

        if (pageNumber < 1 || pageNumber > pages.size()) {
            MessageUtils.sendMessage(player, MessageUtils.formatMessage("&e[LB] &fThis page does not exist."));
            return;
        }

        String pageContent = pages.get(pageNumber - 1);
        String[] lines = pageContent.split("\n");

        MessageUtils.sendMessage(player, MessageUtils.formatMessage("&e[LB] &f[Page " + pageNumber + "/" + pages.size() + "]"));

        for (String line : lines) {
            MessageUtils.sendMessage(player, line);
        }
    }



    /**
     * Retrieves the book ItemStack from the given lectern block.
     *
     * @param block The lectern block.
     * @return The ItemStack representing the book on the lectern, or null if no book is found.
     */
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