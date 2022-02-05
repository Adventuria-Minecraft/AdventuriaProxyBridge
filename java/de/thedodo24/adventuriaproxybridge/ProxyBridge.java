package de.thedodo24.adventuriaproxybridge;

import de.thedodo24.adventuriaproxybridge.listener.PlayerListeners;
import de.thedodo24.adventuriaproxybridge.utils.TabList;
import lombok.Getter;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

@Getter
public class ProxyBridge extends JavaPlugin {


    @Getter
    public static ProxyBridge instance;

    private LuckPerms luckPerms;
    private TabList tabList;


    @Override
    public void onEnable() {
        instance = this;
        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if(provider != null) {
            luckPerms = provider.getProvider();
            getLogger().log(Level.SEVERE, "LuckPerms found!");
        } else {
            getLogger().log(Level.SEVERE, "LuckPerms is not loaded!");
            return;
        }
        tabList = new TabList();
        Bukkit.getPluginManager().registerEvents(new PlayerListeners(), this);
        getLogger().log(Level.INFO, "Loaded plugin");
    }
}
