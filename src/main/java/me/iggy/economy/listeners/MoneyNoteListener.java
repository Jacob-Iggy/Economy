package me.iggy.economy.listeners;

import me.iggy.economy.Economy;
import me.iggy.economy.profile.Profile;
import me.iggy.economy.utils.CC;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class MoneyNoteListener implements Listener {

    @EventHandler
    public static void onPlayerInteract(PlayerInteractEvent event) {

        if (event.getItem() == null || event.getItem().getType() == Material.AIR) return;
        if (event.getHand() != EquipmentSlot.HAND) return;
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (!Economy.getInstance().getEconomyHandler().isMoneyNote(event.getItem())) return;

        Player player = event.getPlayer();
        double amount = Economy.getInstance().getEconomyHandler().getValueOfMoneyNote(event.getItem());
        Economy.getInstance().getEconomyHandler().addMoney(player, amount, "Redeemed Money Note");
        if (event.getItem().getAmount() == 1) {
            player.getInventory().setItemInMainHand(null);
        } else {
            ItemStack item = event.getItem();
            item.setAmount(item.getAmount() - 1);
            player.getInventory().setItemInMainHand(item);
        }
        player.sendMessage(CC.translate(Economy.getInstance().getConfig().getString("moneynote.redeem-message").replaceAll("%amount%", String.valueOf(amount))));

    }

}
