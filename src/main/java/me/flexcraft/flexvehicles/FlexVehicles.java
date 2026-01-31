package me.flexcraft.flexvehicles;

import me.flexcraft.flexvehicles.listeners.VehicleListener;
import me.flexcraft.flexvehicles.vehicle.VehicleManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class FlexVehicles extends JavaPlugin {

    private VehicleManager vehicleManager;

    @Override
    public void onEnable() {
        vehicleManager = new VehicleManager(this);

        Bukkit.getPluginManager().registerEvents(
                new VehicleListener(vehicleManager),
                this
        );

        getLogger().info("FlexVehicles enabled!");
    }

    public VehicleManager getVehicleManager() {
        return vehicleManager;
    }
}
