package me.flexcraft.flexvehicles.vehicle;

import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class VehicleMovementTask implements Runnable {

    private final VehicleManager vehicleManager;
    private final JavaPlugin plugin;

    public VehicleMovementTask(JavaPlugin plugin, VehicleManager vehicleManager) {
        this.plugin = plugin;
        this.vehicleManager = vehicleManager;

        Bukkit.getScheduler().runTaskTimer(plugin, this, 0L, 1L);
    }

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!vehicleManager.hasVehicle(player)) continue;

            ArmorStand stand = vehicleManager.getVehicle(player);
            if (stand == null || stand.isDead()) continue;

            // направление взгляда игрока
            Vector direction = player.getLocation().getDirection().normalize();

            // скорость
            double speed = 0.6;

            // движение
            Vector velocity = direction.multiply(speed);
            stand.teleport(stand.getLocation().add(velocity));

            // поворот транспорта
            stand.setRotation(player.getLocation().getYaw(), 0);
        }
    }
}
