package me.snownetwork.parkour.way;

import me.snownetwork.parkour.Parkour;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

public class timer extends BukkitRunnable {
    Plugin plguin = me.snownetwork.parkour.Parkour.getPlugin(me.snownetwork.parkour.Parkour.class);
    public static HashMap<UUID, Long> date=new HashMap<>();
    Parkour plugins;
    public timer(Parkour plugins){
        this.plguin=plugins;
    }
    @Override
    public void run() {
        Iterator<Map.Entry<UUID, Long>> it = date.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<UUID, Long> entry = it.next();
            entry.setValue(entry.getValue() + 1);
        }

    }
}
