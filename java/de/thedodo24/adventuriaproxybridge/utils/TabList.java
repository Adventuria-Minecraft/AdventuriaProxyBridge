package de.thedodo24.adventuriaproxybridge.utils;

import de.thedodo24.adventuriaproxybridge.ProxyBridge;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.awt.*;

public class TabList {

    private final Scoreboard scoreboard;

    public TabList() {
        scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
    }

    public void setPrefix(Player p) {
        User luckpermsUser = ProxyBridge.getInstance().getLuckPerms().getUserManager().getUser(p.getUniqueId());
        String group = luckpermsUser.getPrimaryGroup();
        Group playerGroup = ProxyBridge.getInstance().getLuckPerms().getGroupManager().getGroup(group);
        int weight = playerGroup.getWeight().orElse(0);
        String prefix = playerGroup.getCachedData().getMetaData().getPrefix();
        String suffix = playerGroup.getCachedData().getMetaData().getSuffix();

        ChatColor color = ChatColor.of(prefix);

        int teamWeight = 100 - weight;
        Team t;
        if(scoreboard.getTeam(teamWeight + "") == null) {
            t = scoreboard.registerNewTeam(teamWeight + "");
            t.setPrefix(suffix + " §8● " + color);
        } else
            t = scoreboard.getTeam(teamWeight + "");
        scoreboard.getTeams().stream().filter(ts -> ts.getEntries().contains(p.getName())).forEach(ts -> ts.removeEntry(p.getName()));
        t.addEntry(p.getName());
        p.setPlayerListName(color + suffix + " §8● " + color + p.getName());
        p.setDisplayName(color + suffix + " §8● " + color + p.getName());

        for(Player all : Bukkit.getOnlinePlayers()) {
            all.setScoreboard(scoreboard);
        }
    }
}
