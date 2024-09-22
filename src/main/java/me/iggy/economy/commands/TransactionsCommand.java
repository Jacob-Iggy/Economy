package me.iggy.economy.commands;

import com.jonahseguin.drink.annotation.Command;
import com.jonahseguin.drink.annotation.Sender;
import me.iggy.economy.profile.TransactionMenu;
import org.bukkit.entity.Player;

public class TransactionsCommand {

    @Command(name = "", desc = "Transactions Command", async = false)
    public static void onCommand(@Sender Player sender) {
        TransactionMenu.openTransactionMenu(sender, 1);
    }

}
