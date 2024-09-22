package me.iggy.economy.profile;

import me.iggy.economy.Economy;
import me.iggy.economy.utils.CC;
import me.iggy.economy.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class TransactionMenu implements Listener {

    public static HashMap<Player, Integer> pageMap = new HashMap<>();

    public static void openTransactionMenu(Player player, int page) {

        Profile profile = Profile.getProfile(player.getUniqueId()).get();
        ArrayList<Transaction> transactions = profile.getTransactionHistory();
        Collections.reverse(transactions);

        if (transactions.isEmpty()) {
            player.sendMessage(CC.translate("&cNo transactions to view."));
            return;
        }

        int lowerBound = page * 9 - 9;
        int upperBound = page * 9 - 1;

        Inventory inventory = Bukkit.createInventory(player, 27, CC.translate("&aTransaction History"));
        for (int i = 0; i < 27; i++) {
            inventory.setItem(i, new ItemBuilder(Material.LIGHT_GRAY_STAINED_GLASS_PANE).name("").build());
        }

        if (upperBound > transactions.size()-1) {
            upperBound = transactions.size()-1;
        }

        for (int i = lowerBound; i <= upperBound; i++) {
            Transaction transaction = transactions.get(i);
            ItemBuilder itemBuilder = new ItemBuilder((transaction.getType().equals("+") ? Material.LIME_WOOL : Material.RED_WOOL));
            itemBuilder.name(CC.translate("&f" + transaction.getReason()));
            itemBuilder.lore(
                    CC.translate("&7Amount: &f" + transaction.getAmount()),
                    CC.translate("&7Type: &f" + transaction.getType())
            );
            inventory.setItem(i + 9, itemBuilder.build());
        }

        if (page > 1) {
            inventory.setItem(18, new ItemBuilder(Material.ARROW).name(CC.translate("&aPrevious Page")).build());
        }

        if (upperBound < transactions.size()-1) {
            inventory.setItem(26, new ItemBuilder(Material.ARROW).name(CC.translate("&aNext Page")).build());
        }

        Bukkit.getScheduler().runTaskLaterAsynchronously(Economy.getInstance(), () -> player.openInventory(inventory), 0L);
        pageMap.put(player, page);

    }

    @EventHandler
    public static void onInventoryClick(InventoryClickEvent event) {
        if (event.getClickedInventory() == null) return;
        if (event.getCurrentItem() == null) return;
        if (!event.getView().getTitle().equals(CC.translate("&aTransaction History"))) return;
        event.setCancelled(true);
        if (event.getCurrentItem().getType() != Material.ARROW) return;

        Player player = (Player) event.getWhoClicked();
        int page = pageMap.get(player);
        if (event.getSlot() == 18) {
            player.closeInventory();
            pageMap.remove(player);
            openTransactionMenu(player, page - 1);
        } else if (event.getSlot() == 26) {
            player.closeInventory();
            pageMap.remove(player);
            openTransactionMenu(player, page + 1);
        }

    }

}
