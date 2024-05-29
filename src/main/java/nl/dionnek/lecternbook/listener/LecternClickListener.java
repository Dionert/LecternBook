package nl.dionnek.lecternbook.listener;

import nl.dionnek.lecternbook.LecternBook;
import org.bukkit.block.Block;
import org.bukkit.block.Lectern;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class LecternClickListener implements Listener {

        /*
         *
         * Lectern Event
         *
         */
        @EventHandler
        public void onLecternInteract(PlayerInteractEvent event) {
            Player player = event.getPlayer();
            Block block = event.getClickedBlock();

            if (block == null || !(block.getState() instanceof Lectern)) {
                return;
            }

            if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
                ItemStack book = LecternBook.inst().getLecternBookManager().getBookFromLectern(block);
                if (book != null) {
                    LecternBook.inst().getLecternBookManager().readBookToPlayer(player, book, 1);
                }
            }
        }


        /*
        *
        * GUI Event
        *
         */
        @EventHandler
        public void onInventoryClick(InventoryClickEvent event) {
            if (event.getView().getTitle().equals("Lectern Book")) {
                event.setCancelled(true);
            }
        }
}