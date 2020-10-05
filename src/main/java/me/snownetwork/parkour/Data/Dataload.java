package me.snownetwork.parkour.Data;

import me.snownetwork.parkour.Parkour;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Dataload {
    private static YamlConfiguration Locationdata;
    public static void LoadData(){
        File file = new File(Parkour.GetInstance().getDataFolder(),"data.yml");
        if(!file.exists()){
            Parkour.GetInstance().saveResource("data.yml",false);

        }
        Locationdata = YamlConfiguration.loadConfiguration(file);
    }
    public static YamlConfiguration getdata(){
        return Locationdata;

    }
    public static void createlocation(String s , Location loc ){
        File file = new File(Parkour.GetInstance().getDataFolder(),"data.yml");
        YamlConfiguration date = YamlConfiguration.loadConfiguration(file);
        date.set(s,loc);
        try {
            date.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}