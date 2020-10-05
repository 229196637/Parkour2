package me.snownetwork.parkour.game;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class GameGather {
    private static ArrayList<UUID> inparkour=new ArrayList<>();//玩家正在跑酷名单
    private static HashMap<UUID,Integer> PlayerPastSize = new HashMap<>();//记录玩家经过记录点数量
    private static HashMap<UUID, String>PlayerPastLoaction=new HashMap<>();//获取玩家对应记录点的数据文件
    private static ArrayList<UUID> inparkourendways = new ArrayList<>();//玩家正在前往终点名单
    private static HashMap<UUID,Location>PlayerPastLoaction2=new HashMap<>();//获取每个玩家要经过的location
    private static HashMap<UUID,Location>PlayerLastLoaction=new HashMap<>();//获取每个玩家经过的上个记录点
    private static HashMap<UUID,ArrayList>item = new HashMap<>();

    public static ArrayList<UUID> getInparkour() {
        return inparkour;
    }

    public static HashMap<UUID, Integer> getPlayerPastSize() {
        return PlayerPastSize;
    }

    public static HashMap<UUID, String> getPlayerPastLoaction() {
        return PlayerPastLoaction;
    }

    public static ArrayList<UUID> getInparkourendways() {
        return inparkourendways;
    }

    public static HashMap<UUID, Location> getPlayerPastLoaction2() {
        return PlayerPastLoaction2;
    }

    public static HashMap<UUID, Location> getPlayerLastLoaction() {
        return PlayerLastLoaction;
    }

    public static HashMap<UUID,ArrayList> getItem() {
        return item;
    }
}
