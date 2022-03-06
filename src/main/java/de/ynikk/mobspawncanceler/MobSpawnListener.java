package de.ynikk.mobspawncanceler;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class MobSpawnListener implements Listener {

    @EventHandler
    public void onMobSpawn(EntitySpawnEvent e) {
        Location loc = e.getLocation();
        for (String key : MobSpawnCanceler.dataConfig.getConfigurationSection("locations").getKeys(false)) {
            String[] keyArgs = key.split(" ");
            Location keyLocation = new Location(Bukkit.getWorld(keyArgs[0]), Integer.parseInt(keyArgs[1]), Integer.parseInt(keyArgs[2]), Integer.parseInt(keyArgs[3]));
            if (loc.distanceSquared(keyLocation) <= (Math.pow(Integer.parseInt(MobSpawnCanceler.dataConfig.getString("locations." + key + ".radius")), 2))) {
                e.setCancelled(true);
            }
        }
    }

}
