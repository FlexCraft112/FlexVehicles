package me.flexcraft.flexvehicles.vehicle;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;

import java.util.HashMap;
import java.util.UUID;

public class VehicleManager {

    private final HashMap<UUID, ArmorStand> activeVehicles = new HashMap<>();

    public void spawnVehicle(Player player) {
        if (activeVehicles.containsKey(player.getUniqueId())) return;

        Location loc = player.getLocation();
        ArmorStand stand = loc.getWorld().spawn(loc, ArmorStand.class);

        stand.setInvisible(true);
        stand.setInvulnerable(true);
        stand.setGravity(false);
        stand.setCustomName("flex_vehicle");
        stand.setCustomNameVisible(false);

        stand.addPassenger(player);
        activeVehicles.put(player.getUniqueId(), stand);
    }

    public void removeVehicle(Player player, boolean giveBackItem) {
        UUID id = player.getUniqueId();
        if (!activeVehicles.containsKey(id)) return;

        ArmorStand stand = activeVehicles.get(id);
        stand.remove();

        activeVehicles.remove(id);

        if (giveBackItem) {
            player.getInventory().addItem(new ItemStack(Material.CARROT_ON_A_STICK));
        }
    }

    public boolean hasVehicle(Player player) {
        return activeVehicles.containsKey(player.getUniqueId());
    }
}
