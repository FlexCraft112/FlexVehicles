package me.flexcraft.flexvehicles.vehicle;

import org.bukkit.Location;
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
        if (player.isDead() || !stand.isValid()) {
            cancel();
            return;
        }

        if (!stand.getPassengers().contains(player)) {
            stand.remove();
            cancel();
            return;
        }

        Location loc = stand.getLocation();
        Vector direction = player.getLocation().getDirection().normalize().multiply(0.6);
        loc.add(direction);
        loc.setY(loc.getY() + 0.3); // ВЗЛЁТ КАК ВЕРТОЛЁТ

        stand.teleport(loc);
    }
}
