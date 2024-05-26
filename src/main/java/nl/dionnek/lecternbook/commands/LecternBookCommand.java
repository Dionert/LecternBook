package nl.dionnek.lecternbook.commands;

import nl.dionnek.lecternbook.LecternBook;
import nl.dionnek.lecternbook.utils.MessageUtils;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class LecternBookCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can execute this command.");
            return false;
        }

        Player player = (Player) sender;

        if (args.length != 1) {
            MessageUtils.sendMessage(player, "&e[LB] &fUsage: /page <pagenumber>");
            return true;
        }

        try {
            int page = Integer.parseInt(args[0]);
            Block block = player.getTargetBlockExact(5);

            // Check if the target block is null or not a lectern
            if (block == null || !LecternBook.inst().getLecternBookManager().isLectern(block)) {
                MessageUtils.sendMessage(player, "&e[LB] &fYou are not looking at a lectern.");
                return true;
            }

            ItemStack book = LecternBook.inst().getLecternBookManager().getBookFromLectern(block);
            if (book != null) {
                LecternBook.inst().getLecternBookManager().readBookToPlayer(player, book, page);
            } else {
                MessageUtils.sendMessage(player, "&e[LB] &fNo book found on the lectern.");
            }
        } catch (NumberFormatException e) {
            MessageUtils.sendMessage(player, "&e[LB] &fInvalid page number.");
        }

        return true;
    }
}
