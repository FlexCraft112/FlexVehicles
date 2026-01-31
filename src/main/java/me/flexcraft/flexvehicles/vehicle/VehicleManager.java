public void spawnVehicle(Player player) {
    if (activeVehicles.containsKey(player.getUniqueId())) return;

    Location loc = player.getLocation();
    ArmorStand stand = loc.getWorld().spawn(loc, ArmorStand.class);

    stand.setInvisible(true);
    stand.setInvulnerable(true);
    stand.setGravity(false);
    stand.setCustomName("flex_heli");
    stand.addPassenger(player);

    activeVehicles.put(player.getUniqueId(), stand);

    new VehicleMovementTask(player, stand).runTaskTimer(
        Bukkit.getPluginManager().getPlugin("FlexVehicles"),
        0L, 1L
    );
}
