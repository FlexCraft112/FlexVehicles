package me.flexcraft.flexvehicles.vehicle;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class VehicleManager {

    private final HashMap<UUID, ArmorStand> activeVehicles = new HashMap<>();
    private final JavaPlugin plugin;

    public VehicleManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void spawnVehicle(Player player) {
        if (activeVehicles.containsKey(player.getUniqueId())) return;

        Location loc = player.getLocation();
        ArmorStand stand = loc.getWorld().spawn(loc, ArmorStand.class);

        stand.setInvisible(true);
        stand.setInvulnerable(true);
        stand.setGravity(false);
        stand.setMarker(true);
        stand.setCustomName("flex_vehicle");

        stand.addPassenger(player);
        activeVehicles.put(player.getUniqueId(), stand);

        new VehicleMovementTask(player, stand).runTaskTimer(plugin, 0L, 1L);
    }

    public void removeVehicle(Player player, boolean giveBackItem) {
        UUID id = player.getUniqueId();
        if (!activeVehicles.containsKey(id)) return;

        ArmorStand stand = activeVehicles.get(id);
        stand.remove();

        activeVehicles.remove(id);
    }

    public boolean hasVehicle(Player player) {
        return activeVehicles.containsKey(player.getUniqueId());
    }
}
