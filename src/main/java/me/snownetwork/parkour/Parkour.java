package me.snownetwork.parkour;

import me.snownetwork.parkour.Check.Check;
import me.snownetwork.parkour.Command.*;
import me.snownetwork.parkour.Data.Dataload;
import me.snownetwork.parkour.game.gamelisten;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public final class Parkour extends JavaPlugin {
    private static Parkour instance;
    public static Parkour GetInstance(){
        return instance;
    }

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new gamelisten(), this);
        getConfig().options().copyDefaults();
        saveDefaultConfig();//这两行为加载config配置文件
        instance=this;
        getCommand("pklast").setExecutor(new pklast());
        getCommand("pkset").setExecutor(new Setting());
        getCommand("pkcheck").setExecutor(new Checkgame());
        getCommand("pkhelp").setExecutor(new pkhelp());
        getCommand("pkreset").setExecutor(new pkreset());
        getServer().getLogger().info("[SnowGame-Parkour]:§a跑酷插件已经加载");
        Dataload.LoadData();//加载位置文件
        Check.checkgame();//检查游戏完整性
        BukkitTask task = new me.snownetwork.parkour.way.timer(this).runTaskTimer(this, 0, 20);
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        getServer().getLogger().info("[SnowGame-Parkour]:§a跑酷插件已经关闭");
        // Plugin shutdown logic
    }
}
