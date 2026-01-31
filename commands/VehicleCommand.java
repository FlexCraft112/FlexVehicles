package me.flexcraft.flexvehicles.commands;

import me.flexcraft.flexvehicles.economy.EconomyManager;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class VehicleCommand implements CommandExecutor {

    private final double price = 5000;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players.");
            return true;
        }

        var econ = EconomyManager.getEconomy();
        if (econ == null) {
            player.sendMessage("§cEconomy not found.");
            return true;
        }

        double balance = econ.getBalance(player);

        if (balance < price) {
            player.sendMessage("§cНужно 5000 монет.");
            return true;
        }

        econ.withdrawPlayer(player, price);

        player.getInventory().addItem(new ItemStack(Material.CARROT_ON_A_STICK));
        player.sendMessage("§aВертолёт куплен!");

        return true;
    }
}
