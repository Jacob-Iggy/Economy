package me.iggy.economy;

import me.iggy.economy.profile.Profile;
import me.iggy.economy.profile.Transaction;
import me.iggy.economy.utils.CC;
import me.iggy.economy.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class EconomyHandler {

    public EconomyHandler() {}

    public void addMoney(Player player, double amount, String reason) {
        Profile profile = Profile.getProfile(player.getUniqueId()).get();
        profile.setBalance(profile.getBalance() + amount);
        Transaction transaction = new Transaction(amount, "Received", reason);
        profile.getTransactionHistory().add(transaction);
        profile.save();
    }

    public void removeMoney(Player player, double amount, String reason) {
        Profile profile = Profile.getProfile(player.getUniqueId()).get();
        profile.setBalance(profile.getBalance() - amount);
        Transaction transaction = new Transaction(amount, "Lost", reason);
        profile.getTransactionHistory().add(transaction);
        profile.save();
    }

    public void transferMoney(Player sender, Player receiver, double amount) {
        Profile sProfile = Profile.getProfile(sender.getUniqueId()).get();
        Profile rProfile = Profile.getProfile(receiver.getUniqueId()).get();

        sProfile.setBalance(sProfile.getBalance() - amount);
        rProfile.setBalance(rProfile.getBalance() + amount);

        Transaction sTransaction = new Transaction(amount, "Lost", "Paid " + receiver.getDisplayName());
        Transaction rTransaction = new Transaction(amount, "Received", "Sent From " + sender.getDisplayName());

        sProfile.getTransactionHistory().add(sTransaction);
        rProfile.getTransactionHistory().add(rTransaction);

        sProfile.save();
        rProfile.save();
    }

    public void addBankMoney(Player player, double amount, String reason) {
        Profile profile = Profile.getProfile(player.getUniqueId()).get();
        profile.setBankBalance(profile.getBankBalance() + amount);
        Transaction transaction = new Transaction(amount, "Received", reason);
        profile.getTransactionHistory().add(transaction);
        profile.save();
    }

    public void removeBankMoney(Player player, double amount, String reason) {
        Profile profile = Profile.getProfile(player.getUniqueId()).get();
        profile.setBankBalance(profile.getBankBalance() - amount);
        Transaction transaction = new Transaction(amount, "Lost", reason);
        profile.getTransactionHistory().add(transaction);
        profile.save();
    }

    public void transferToBank(Player player, double amount) {
        Profile profile = Profile.getProfile(player.getUniqueId()).get();
        profile.setBalance(profile.getBalance() - amount);
        profile.setBankBalance(profile.getBankBalance() + amount);
        Transaction transaction = new Transaction(amount, "Deposit", "Deposit to Bank");
        profile.getTransactionHistory().add(transaction);
        profile.save();
    }

    public void withdrawFromBank(Player player, double amount) {
        Profile profile = Profile.getProfile(player.getUniqueId()).get();
        profile.setBankBalance(profile.getBankBalance() - amount);
        profile.setBalance(profile.getBalance() + amount);
        Transaction transaction = new Transaction(amount, "Withdraw", "Withdraw from Bank");
        profile.getTransactionHistory().add(transaction);
        profile.save();
    }

    public ItemStack createMoneyNote(double amount) {
        ItemBuilder itemBuilder = new ItemBuilder(Material.valueOf(Economy.getInstance().getConfig().getString("moneynote.item.material")), 1);
        itemBuilder.name(CC.translate(Economy.getInstance().getConfig().getString("moneynote.item.name").replaceAll("%amount%", String.valueOf(amount))));
        itemBuilder.lore(CC.translate(Economy.getInstance().getConfig().getStringList("moneynote.item.lore")));
        ItemMeta meta = itemBuilder.build().getItemMeta();
        meta.getPersistentDataContainer().set(new NamespacedKey(Economy.getInstance(), "money-amount"), PersistentDataType.DOUBLE, (Double) amount);
        itemBuilder.meta(meta);
        return itemBuilder.build();
    }

    public boolean isMoneyNote(ItemStack itemStack) {
        if (!itemStack.hasItemMeta()) return false;
        return itemStack.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Economy.getInstance(), "money-amount"), PersistentDataType.DOUBLE);
    }

    public double getValueOfMoneyNote(ItemStack itemStack) {
        return (double) itemStack.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Economy.getInstance(), "money-amount"), PersistentDataType.DOUBLE);
    }

}
