package me.snownetwork.parkour.way;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

public class hd {
    private static ArmorStand hologram;
    private static ArmorStand hologramline2;
    public static void createhologram(Location loc, String line2, String line1){
        hologram = (ArmorStand)loc.getWorld().spawnEntity(loc.add(0,+0.3,0), EntityType.ARMOR_STAND);
        hologram.setArms(false);
        hologram.setGravity(false);
        hologram.setCustomNameVisible(true);
        hologram.setCustomName(line2);
        hologram.setVisible(false);
        hologram.setSmall(true);
        hologram.setBasePlate(false);

        hologramline2 = (ArmorStand)loc.getWorld().spawnEntity(loc.add(0,+0.5,0), EntityType.ARMOR_STAND);
        hologramline2.setArms(false);
        hologramline2.setGravity(false);
        hologramline2.setCustomNameVisible(true);
        hologramline2.setCustomName(line1);
        hologramline2.setVisible(false);
        hologramline2.setSmall(true);
        hologramline2.setBasePlate(false);

    }
    public static ArmorStand getHologram(){
        return hologram;
    }


}
