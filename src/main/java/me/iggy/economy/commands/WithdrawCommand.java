package me.iggy.economy.commands;

import com.jonahseguin.drink.annotation.Command;
import com.jonahseguin.drink.annotation.Sender;
import me.iggy.economy.Economy;
import me.iggy.economy.utils.CC;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class WithdrawCommand {

    @Command(name = "", desc = "Withdraw Command", async = false)
    public static void onCommand(@Sender Player sender, double amount) {

        if (Economy.getInstance().getEconomyHandler().getBalance(sender) < amount) {
            sender.sendMessage(CC.translate(Economy.getInstance().getConfig().getString("insufficient-funds-message")));
            return;
        }

        if (sender.getInventory().firstEmpty() == -1) {
            sender.sendMessage(CC.translate("&cInventory is full."));
            return;
        }

        Economy.getInstance().getEconomyHandler().removeMoney(sender, amount, "Withdrawal to Money Note");
        ItemStack item = Economy.getInstance().getEconomyHandler().createMoneyNote(amount);
        sender.getInventory().addItem(item);

    }

}
