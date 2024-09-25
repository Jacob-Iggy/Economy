package me.iggy.economy.listeners;

import me.iggy.economy.Economy;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class DeathListener implements Listener {

    @EventHandler
    public static void onDeath(PlayerDeathEvent event) {

        if (!Economy.getInstance().getConfig().getBoolean("drop-money-on-death")) return;

        ItemStack money = Economy.getInstance().getEconomyHandler().createMoneyNote(Economy.getInstance().getEconomyHandler().getBalance(event.getEntity()));
        Economy.getInstance().getEconomyHandler().setMoney(event.getEntity(), 0, "Died");
        event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), money);

    }

}
