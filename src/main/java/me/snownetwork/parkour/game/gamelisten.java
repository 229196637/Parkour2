package me.snownetwork.parkour.game;

import me.snownetwork.parkour.Data.Dataload;
import me.snownetwork.parkour.Parkour;
import me.snownetwork.parkour.way.timer;
import me.snownetwork.parkour.way.tittle;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class gamelisten implements Listener {
    private int size;
    @EventHandler(priority = EventPriority.HIGH)
    public void PlayerMove(PlayerMoveEvent e){
        if(Dataload.getdata().get("startlocation")!=null){
            Player p = e.getPlayer();
            Location loc = (Location) Dataload.getdata().get("startlocation");
            int x = loc.getBlockX();
            int y=loc.getBlockY();
            int z=loc.getBlockZ();
            //如果玩家刚开始跑酷
            if(!GameGather.getInparkour().contains(p.getUniqueId())){
                if(p.getLocation().getBlockX()==x&&p.getLocation().getBlockY()==y&&p.getLocation().getBlockZ()==z){
                    p.sendMessage("§f[§b跑酷系统§f]:§a开始跑酷！");
                    timer.date.put(p.getUniqueId(),0L);
                    GameGather.getInparkour().add(p.getUniqueId());//添加玩家到跑酷中列表
                    GameGather.getPlayerPastSize().put(p.getUniqueId(),1);//添加玩家需要经过点1
                   // GameGather.getPlayerPastLoaction().put(p,"pastlocation1");//添加玩家要经过目标点
                    //GameGather.getPlayerLastLoaction().put(p,(Location) Dataload.getdata().get("startlocation"));//添加玩家上一个记录点为起点
                    giveGameThing(p);//给予玩家跑酷物品
                }
            }else {
                if(!GameGather.getInparkourendways().contains(p.getUniqueId())){
                    if(GameGather.getInparkour().contains(p.getUniqueId())){
                        Location loc2 = (Location)Dataload.getdata().get("pastlocation"+GameGather.getPlayerPastSize().get(p.getUniqueId()));
                        if(p.getLocation().getBlockY()==loc2.getBlockY()&p.getLocation().getBlockX()==loc2.getBlockX()&p.getLocation().getBlockZ()==loc2.getBlockZ()){
                            p.sendMessage("§f[§b跑酷系统§f]:§a恭喜您经过了该记录点，用时：§e"+timer.date.get(p.getUniqueId())+"§a秒");
                            GameGather.getPlayerPastSize().replace(p.getUniqueId(),GameGather.getPlayerPastSize().get(p.getUniqueId())+1);
                            if(GameGather.getPlayerPastSize().get(p.getUniqueId())-1==size){
                                GameGather.getInparkourendways().add(p.getUniqueId());
                            }


                        }
                    }
                }
                /*Iterator<Map.Entry<Player, String>> it2 = GameGather.getPlayerPastLoaction().entrySet().iterator();
                while (it2.hasNext()) {
                    Map.Entry<Player, String > entry = it2.next();
                    GameGather.getPlayerPastLoaction2().put(p,(Location) Dataload.getdata().get(entry.getValue()));
                    if(!GameGather.getInparkourendways().contains(p)){
                        if(p.getLocation().getBlockX()==GameGather.getPlayerPastLoaction2().get(p).getBlockX()&&p.getLocation().getBlockY()==GameGather.getPlayerPastLoaction2().get(p).getBlockY()&&p.getLocation().getBlockZ()==GameGather.getPlayerPastLoaction2().get(p).getBlockZ()){
                            p.sendMessage("§f[§b跑酷系统§f]:§a恭喜您经过了该记录点，用时：§e"+timer.date.get(p)+"§a秒");
                            Iterator<Map.Entry<Player, Integer>> it = GameGather.getPlayerPastSize().entrySet().iterator();
                            Iterator<Map.Entry<Player, Location>> it3 = GameGather.getPlayerPastLoaction2().entrySet().iterator();
                            Iterator<Map.Entry<Player, Location>> it4 = GameGather.getPlayerLastLoaction().entrySet().iterator();
                            while (it.hasNext()) {
                                Map.Entry<Player, Integer> entry2 = it.next();
                                if(entry2.getValue()!=size){
                                    entry2.setValue(entry2.getValue() + 1);
                                    entry.setValue("pastlocation"+GameGather.getPlayerPastSize().get(p));
                                    while (it3.hasNext()){
                                        Map.Entry<Player, Location> entry3 = it3.next();
                                        while (it4.hasNext()) {
                                            Map.Entry<Player, Location> entry4 = it4.next();
                                            entry4.setValue(entry3.getValue());
                                        }
                                        entry3.setValue((Location) Dataload.getdata().get(entry.getValue()));
                                    }
                                }else {
                                    GameGather.getInparkourendways().add(p);//添加玩家到跑向终点集合
                                }
                            }

                        }
                    }


                }*/
                for(int i=0;i<=100;i++){
                    if(Dataload.getdata().get("pastlocation"+i)!=null){
                        size=i;//获取记录点的数量
                    }


                }
                if(GameGather.getInparkour().contains(p.getUniqueId())){
                    Location endlocation =(Location) Dataload.getdata().get("endlocation");
                    if(p.getLocation().getBlockX()==endlocation.getBlockX()&&p.getLocation().getBlockY()==endlocation.getBlockY()&&p.getLocation().getBlockZ()==endlocation.getBlockZ()) {
                        if(GameGather.getInparkourendways().contains(p.getUniqueId())){
                            p.sendMessage("§f[§b跑酷系统§f]:§a恭喜您完成了跑酷！用时：§e" + timer.date.get(p.getUniqueId()) + "§a秒");
                            p.playSound(p.getLocation(), Sound.valueOf("LEVEL_UP"), 1.0f, 1.0f);
                            tittle.sendTitle(p, "§a恭喜您完成了本次跑酷！", "§b用时:§e" + timer.date.get(p.getUniqueId()) + "§a秒", 20);
                            //下面为重置玩家
                            reSetPlayer(p.getUniqueId());
                        }else {
                            p.sendMessage("§f[§b跑酷系统§f]:§4你遗落了记录点！重返上一个记录点！");
                            if(GameGather.getPlayerPastSize().get(p.getUniqueId())==1){
                                p.teleport((Location)Dataload.getdata().get("startlocation"));

                            }else{
                                int a =GameGather.getPlayerPastSize().get(p.getUniqueId())-1;
                                p.teleport((Location)Dataload.getdata().get("pastlocation"+a));
                            }
                        }
                    }
                }




            }

        }


    }
    @EventHandler(priority = EventPriority.HIGH)
    public void PlayerMode(PlayerGameModeChangeEvent e){
        if(!e.getPlayer().hasPermission("snow.admin")){
            e.setCancelled(true);
        }
    }
    @EventHandler(priority = EventPriority.HIGH)
    public void PlayerFly(PlayerToggleFlightEvent e){
        if(!e.getPlayer().hasPermission("snow.admin")){
            if(GameGather.getInparkour().contains(e.getPlayer().getUniqueId())){
                e.setCancelled(true);
                e.getPlayer().sendMessage("§4跑酷中不允许飞行！");
            }
        }

    }
    public static void reSetPlayer(UUID uuid){
        GameGather.getInparkour().remove(uuid);
        GameGather.getInparkourendways().remove(uuid);
        GameGather.getPlayerPastSize().remove(uuid);
        //GameGather.getPlayerPastLoaction().remove(p);
        //GameGather.getPlayerPastLoaction2().remove(p);
        //GameGather.getPlayerLastLoaction().remove(p);
        timer.date.remove(uuid);
        saveInventory(Objects.requireNonNull(Bukkit.getPlayer(uuid)));
    }
    public static void saveInventory(Player p ){
        p.getInventory().clear();
        ArrayList<ItemStack> item = GameGather.getItem().get(p.getUniqueId());
        for(int i=0;i<item.size();i++){
            if(item.get(i)!=null){
                p.getInventory().setItem(i,item.get(i));
            }
        }
        GameGather.getItem().remove(p.getUniqueId());
    }
    public static void giveGameThing(Player p ){
        ArrayList<ItemStack> saveitem=new ArrayList<>();
        for(int i=0;i<36;i++){
            saveitem.add(p.getInventory().getItem(i));
        }
        GameGather.getItem().put(p.getUniqueId(),saveitem);
        p.getInventory().clear();
        createitem(p);//给予跑酷物品
    }
    public static void createitem(Player p){
        ItemStack itemStack = new ItemStack(Material.valueOf("IRON_PLATE"));
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("§a返回上一个记录点");
        itemStack.setItemMeta(itemMeta);
        p.getInventory().setItem(3,itemStack);

        ItemStack itemStack2 = new ItemStack(Material.valueOf("WOOD_DOOR"));
        ItemMeta itemMeta2 = itemStack2.getItemMeta();
        itemMeta2.setDisplayName("§e重置");
        itemStack2.setItemMeta(itemMeta2);
        p.getInventory().setItem(4,itemStack2);


        ItemStack itemStack3 = new ItemStack(Material.valueOf("BED"));
        ItemMeta itemMeta3 = itemStack3.getItemMeta();
        itemMeta3.setDisplayName("§c结束跑酷");
        itemStack3.setItemMeta(itemMeta3);
        p.getInventory().setItem(5,itemStack3);




    }
    @EventHandler
    public void PlayerUseThing(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if(GameGather.getInparkour().contains(p.getUniqueId())){
            if(e.getAction()==Action.RIGHT_CLICK_BLOCK||e.getAction()==Action.RIGHT_CLICK_AIR){
                try {
                    switch (Objects.requireNonNull(e.getItem()).getItemMeta().getDisplayName()){
                        case"§a返回上一个记录点":
                            int a =GameGather.getPlayerPastSize().get(p.getUniqueId())-1;
                            if(GameGather.getPlayerPastSize().get(p.getUniqueId())==1){
                                p.teleport((Location)Dataload.getdata().get("startlocation"));
                            }else {
                                p.teleport((Location) Dataload.getdata().get("pastlocation"+a));
                            }
                            p.sendMessage("§f[§b跑酷系统§f]:§a您已成功返回");
                            break;
                        case "§e重置":
                            reSetPlayer(p.getUniqueId());
                            p.teleport( (Location) Dataload.getdata().get("startlocation"));
                            break;
                        case "§c结束跑酷":
                            reSetPlayer(p.getUniqueId());
                            p.sendMessage("§f[§b跑酷系统§f]:§a您已取消此次跑酷!");
                    }
                }catch (NullPointerException et){
                }
            }

        }

    }
    @EventHandler(priority = EventPriority.HIGH)
    public void PlayerQuit(PlayerQuitEvent e){
        if(GameGather.getInparkour().contains(e.getPlayer().getUniqueId())){
            reSetPlayer(e.getPlayer().getUniqueId());

        }

    }


}
