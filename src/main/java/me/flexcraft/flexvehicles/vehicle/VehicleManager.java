package me.flexcraft.flexvehicles.vehicle;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

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

        Location loc = player.getLocation().subtract(0, 1.2, 0);
        ArmorStand stand = loc.getWorld().spawn(loc, ArmorStand.class);

        stand.setInvisible(true);
        stand.setInvulnerable(true);
        stand.setGravity(false);
        stand.setCustomName("flex_vehicle");
        stand.setCustomNameVisible(false);
        stand.setRotation(player.getLocation().getYaw(), 0);

        stand.addPassenger(player);
        activeVehicles.put(player.getUniqueId(), stand);
    }

    public void removeVehicle(Player player, boolean giveBackItem) {
        UUID id = player.getUniqueId();
        if (!activeVehicles.containsKey(id)) return;

        ArmorStand stand = activeVehicles.get(id);
        if (stand != null && !stand.isDead()) stand.remove();

        activeVehicles.remove(id);

        if (giveBackItem) {
            Material mat = Material.valueOf(plugin.getConfig().getString("vehicle.item"));
            player.getInventory().addItem(new ItemStack(mat));
        }
    }

    public boolean hasVehicle(Player player) {
        return activeVehicles.containsKey(player.getUniqueId());
    }

    public ArmorStand getVehicle(Player player) {
        return activeVehicles.get(player.getUniqueId());
    }
}
