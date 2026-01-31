package me.flexcraft.flexvehicles;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class VehicleCommand implements CommandExecutor {

    private final Economy econ;
    private final double price = 5000;

    public VehicleCommand(Economy econ) {
        this.econ = econ;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) return true;
        Player p = (Player) sender;

        if (econ.getBalance(p) < price) {
            p.sendMessage("§cNeed 5000 coins");
            return true;
        }

        econ.withdrawPlayer(p, price);
        p.getInventory().addItem(new ItemStack(Material.CARROT_ON_A_STICK));
        p.sendMessage("§aHelicopter purchased!");
        return true;
    }
}
