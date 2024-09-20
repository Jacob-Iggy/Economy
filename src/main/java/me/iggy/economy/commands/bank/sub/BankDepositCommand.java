package me.iggy.economy.commands.bank.sub;

import com.jonahseguin.drink.annotation.Command;
import com.jonahseguin.drink.annotation.Sender;
import me.iggy.economy.Economy;
import me.iggy.economy.utils.CC;
import org.bukkit.entity.Player;

public class BankDepositCommand {

    @Command(name = "deposit", desc = "Bank Deposit Command", async = false)
    public static void onCommand(@Sender Player sender, double amount) {

        if (Economy.getInstance().getEconomyHandler().getBalance(sender) < amount) {
            sender.sendMessage(CC.translate(Economy.getInstance().getConfig().getString("insufficient-funds-message")));
            return;
        }

        Economy.getInstance().getEconomyHandler().transferToBank(sender, amount);
        sender.sendMessage(CC.translate(Economy.getInstance().getConfig().getString("bank-deposit-message").replaceAll("%amount%", String.valueOf(amount))));

    }

}
