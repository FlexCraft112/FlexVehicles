package me.flexcraft.flexvehicles.vehicle;

import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class VehicleMovementTask extends BukkitRunnable {

    private final Player player;
    private final ArmorStand stand;

    public VehicleMovementTask(Player player, ArmorStand stand) {
        this.player = player;
        this.stand = stand;
    }

    @Override
    public void run() {
        if (!player.isOnline() || stand.isDead()) {
            cancel();
            return;
        }

        // Направление взгляда
        Vector direction = player.getLocation().getDirection().normalize();

        // Горизонтальное движение
        Vector move = direction.multiply(0.6);

        // Подъём (SPACE)
        if (player.isJumping()) {
            move.setY(0.4);
        }
        // Спуск (SHIFT)
        else if (player.isSneaking()) {
            move.setY(-0.4);
        }
        else {
            move.setY(0);
        }

        stand.setVelocity(move);
    }
}
