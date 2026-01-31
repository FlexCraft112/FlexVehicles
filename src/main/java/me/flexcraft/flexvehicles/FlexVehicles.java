package me.flexcraft.flexvehicles;

import me.flexcraft.flexvehicles.listeners.VehicleListener;
import me.flexcraft.flexvehicles.vehicle.VehicleManager;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class FlexVehicles extends JavaPlugin {

    private static Economy econ;
    private VehicleManager manager;

    @Override
    public void onEnable() {
        if (!setupEconomy()) {
            getLogger().severe("Vault not found!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        manager = new VehicleManager();
        Bukkit.getPluginManager().registerEvents(new VehicleListener(manager), this);
        getCommand("vehicle").setExecutor(new VehicleCommand(econ));
    }

    private boolean setupEconomy() {
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) return false;
        econ = rsp.getProvider();
        return econ != null;
    }
}
