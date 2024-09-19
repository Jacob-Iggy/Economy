package me.iggy.economy.listeners;

import me.iggy.economy.profile.Profile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public static void onJoin(PlayerJoinEvent event) {
        if (Profile.getProfile(event.getPlayer().getUniqueId()).isEmpty()) {
            Profile profile = new Profile(event.getPlayer().getUniqueId());
            profile.save();
        }
    }

}
