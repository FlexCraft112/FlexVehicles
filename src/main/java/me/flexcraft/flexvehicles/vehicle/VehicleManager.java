package me.flexcraft.flexvehicles.vehicle;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;

import java.util.HashMap;
import java.util.UUID;

public class VehicleManager {

    private final HashMap<UUID, ArmorStand> vehicles = new HashMap<>();

    public void spawnVehicle(Player player) {
        if (vehicles.containsKey(player.getUniqueId())) return;

        Location loc = player.getLocation();
        ArmorStand stand = loc.getWorld().spawn(loc, ArmorStand.class);

        stand.setInvisible(true);
        stand.setGravity(false);
        stand.setInvulnerable(true);

        stand.addPassenger(player);
        vehicles.put(player.getUniqueId(), stand);
    }

    public void removeVehicle(Player player) {
        UUID id = player.getUniqueId();
        if (!vehicles.containsKey(id)) return;

        vehicles.get(id).remove();
        vehicles.remove(id);

        player.getInventory().addItem(new ItemStack(Material.CARROT_ON_A_STICK));
    }

    public boolean hasVehicle(Player player) {
        return vehicles.containsKey(player.getUniqueId());
    }
}
