package me.flexcraft.flexvehicles;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class VehicleCommand implements CommandExecutor {

    private final double PRICE = 5000;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;

        if (FlexVehicles.getEconomy().getBalance(player) < PRICE) {
            player.sendMessage("§cНужно 5000 монет!");
            return true;
        }

        FlexVehicles.getEconomy().withdrawPlayer(player, PRICE);

        ItemStack heli = new ItemStack(Material.CARROT_ON_A_STICK);
        player.getInventory().addItem(heli);

        player.sendMessage("§aВы купили вертолёт!");
        return true;
    }
}
