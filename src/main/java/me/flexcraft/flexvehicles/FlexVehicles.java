package me.flexcraft.flexvehicles;

import org.bukkit.plugin.java.JavaPlugin;
import me.flexcraft.flexvehicles.listeners.VehicleListener;
import me.flexcraft.flexvehicles.vehicle.VehicleManager;

public class FlexVehicles extends JavaPlugin {

    private static FlexVehicles instance;
    private VehicleManager vehicleManager;

    @Override
    public void onEnable() {
        instance = this;
        vehicleManager = new VehicleManager();

        getServer().getPluginManager().registerEvents(new VehicleListener(vehicleManager), this);

        getLogger().info("FlexVehicles enabled");
    }

    public static FlexVehicles getInstance() {
        return instance;
    }
}
