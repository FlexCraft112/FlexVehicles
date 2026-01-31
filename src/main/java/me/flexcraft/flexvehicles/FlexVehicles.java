package me.flexcraft.flexvehicles;

import me.flexcraft.flexvehicles.listeners.VehicleListener;
import me.flexcraft.flexvehicles.vehicle.VehicleManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.economy.Economy;

public final class FlexVehicles extends JavaPlugin {

    private static Economy econ;
    private VehicleManager vehicleManager;

    @Override
    public void onEnable() {
        if (!setupEconomy()) {
            getLogger().severe("Vault не найден! Плагин выключен.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        vehicleManager = new VehicleManager(this);

        Bukkit.getPluginManager().registerEvents(
                new VehicleListener(vehicleManager),
                this
        );

        getCommand("vehicle").setExecutor(new VehicleCommand());

        getLogger().info("FlexVehicles enabled!");
    }

    public static Economy getEconomy() {
        return econ;
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) return false;
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) return false;
        econ = rsp.getProvider();
        return econ != null;
    }
}
