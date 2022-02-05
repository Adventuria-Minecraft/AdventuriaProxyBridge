package de.thedodo24.adventuriaproxybridge.listener;

import de.thedodo24.adventuriaproxybridge.ProxyBridge;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListeners implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        ProxyBridge.getInstance().getTabList().setPrefix(p);
    }
}
