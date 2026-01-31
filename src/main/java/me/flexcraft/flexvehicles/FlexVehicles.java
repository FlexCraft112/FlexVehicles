package me.flexcraft.flexvehicles;

import me.flexcraft.flexvehicles.listeners.VehicleListener;
import me.flexcraft.flexvehicles.vehicle.VehicleManager;
import me.flexcraft.flexvehicles.vehicle.VehicleMovementTask;
import org.bukkit.plugin.java.JavaPlugin;

public class FlexVehicles extends JavaPlugin {

    private VehicleManager vehicleManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        vehicleManager = new VehicleManager(this);
        new VehicleMovementTask(this, vehicleManager);

        getServer().getPluginManager().registerEvents(
                new VehicleListener(vehicleManager),
                this
        );

        getLogger().info("FlexVehicles enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("FlexVehicles disabled");
    }
}
