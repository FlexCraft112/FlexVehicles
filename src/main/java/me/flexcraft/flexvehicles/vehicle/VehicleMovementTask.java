package me.flexcraft.flexvehicles.vehicle;

import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class VehicleMovementTask extends BukkitRunnable {

    private final VehicleManager manager;

    public VehicleMovementTask(VehicleManager manager) {
        this.manager = manager;
    }

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!manager.hasVehicle(player)) continue;

            ArmorStand stand = manager.getVehicle(player);
            if (stand == null) continue;

            Vector dir = player.getLocation().getDirection().normalize();
            Vector velocity = dir.multiply(0.6);

            stand.teleport(stand.getLocation().add(velocity));
        }
    }
}
