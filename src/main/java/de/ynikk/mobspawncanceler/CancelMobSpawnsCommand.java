package de.ynikk.mobspawncanceler;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class CancelMobSpawnsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You need to be a player!");
            return false;
        }

        Player p = (Player) sender;

        if (args.length != 1) {

            Location loc = p.getLocation();
            String locString = loc.getWorld().getName() + " " + loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ();

            int radius;
            try {
                radius = Integer.parseInt(args[0]);
            } catch (NumberFormatException ex) {
                p.sendMessage(ChatColor.RED + "Invalid Radius: /cancelmobspawns <Radius>");
                return false;
            }

            MobSpawnCanceler.dataConfig.set("locations." + locString + ".radius", radius);
            try {
                MobSpawnCanceler.dataConfig.save(MobSpawnCanceler.dataFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

            p.sendMessage(ChatColor.GREEN + "You set a MobCancelPoint!");

        } else {
            p.sendMessage(ChatColor.RED + "Invalid Syntax: /cancelmobspawns <Radius>");
        }

        return true;
    }

}
