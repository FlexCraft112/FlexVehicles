package me.flexcraft.flexvehicles.listeners;

import me.flexcraft.flexvehicles.vehicle.VehicleManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.inventory.EquipmentSlot;

public class VehicleListener implements Listener {

    private final VehicleManager manager;

    public VehicleListener(VehicleManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onUse(PlayerInteractEvent e) {
        if (e.getHand() != EquipmentSlot.HAND) return;
        if (!(e.getPlayer() instanceof Player player)) return;
        if (e.getItem() == null) return;

        if (e.getItem().getType() == Material.CARROT_ON_A_STICK) {
            e.setCancelled(true);

            if (!manager.hasVehicle(player)) {
                e.getItem().setAmount(e.getItem().getAmount() - 1);
                manager.spawnVehicle(player);
            }
        }
    }

    @EventHandler
    public void onExit(VehicleExitEvent e) {
        if (!(e.getExited() instanceof Player player)) return;
        manager.removeVehicle(player, true);
    }
}
