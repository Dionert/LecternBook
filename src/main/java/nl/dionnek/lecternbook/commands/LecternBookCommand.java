package nl.dionnek.lecternbook.commands;

import nl.dionnek.lecternbook.LecternBook;
import nl.dionnek.lecternbook.gui.LecternBookGUI;
import nl.dionnek.lecternbook.utils.MessageUtils;
import org.bukkit.block.Block;
import org.bukkit.block.Lectern;
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

        if (args.length == 0) {
            MessageUtils.sendMessage(player, "&e[LB] &fUsage: /lb page <pagenumber> || /lb gui");
            return true;
        }






        if (args.length == 2 && args[0].equalsIgnoreCase("page")) {
            try {
                int page = Integer.parseInt(args[1]);
                Block block = player.getTargetBlockExact(5);

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






        if (args.length == 1 && args[0].equalsIgnoreCase("gui")) {
            Block block = player.getTargetBlockExact(5);

            if (block == null || !(block.getState() instanceof Lectern)) {
                MessageUtils.sendMessage(player, "&e[LB] &fYou are not looking at a lectern.");
                return true;
            }

            ItemStack book = LecternBook.inst().getLecternBookManager().getBookFromLectern(block);
            if (book != null) {
                LecternBookGUI gui = new LecternBookGUI(player, book);
                gui.openBookGui();
            } else {
                MessageUtils.sendMessage(player, "&e[LB] &fNo book found on the lectern.");
            }

            return true;
        }

        MessageUtils.sendMessage(player, "&e[LB] &fUsage: /lb page <pagenumber> or /lb gui");
        return true;
    }
}