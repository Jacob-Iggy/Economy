package me.iggy.economy.commands.bank.sub;

import com.jonahseguin.drink.annotation.Command;
import com.jonahseguin.drink.annotation.Sender;
import me.iggy.economy.Economy;
import me.iggy.economy.utils.CC;
import org.bukkit.entity.Player;

public class BankWithdrawCommand {

    @Command(name = "withdraw", desc = "Bank Withdraw Command", async = false)
    public static void onCommand(@Sender Player sender, double amount) {

        if (Economy.getInstance().getEconomyHandler().getBankBalance(sender) < amount) {
            sender.sendMessage(CC.translate(Economy.getInstance().getConfig().getString("insufficient-funds-message")));
            return;
        }

        Economy.getInstance().getEconomyHandler().withdrawFromBank(sender, amount);
        sender.sendMessage(CC.translate(Economy.getInstance().getConfig().getString("bank-withdraw-message").replaceAll("%amount%", String.valueOf(amount))));

    }

}
