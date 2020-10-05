package me.snownetwork.parkour.Command;

import me.snownetwork.parkour.Data.Dataload;
import me.snownetwork.parkour.way.hd;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Setting implements CommandExecutor {
    private static int size = 0;
    private boolean start =false;
    private boolean past=false;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player=(Player)sender;
            if(args.length>=1){
                switch (args[0]){
                    case"start":
                        Dataload.createlocation("startlocation",player.getLocation());
                        World world = Bukkit.getWorld(player.getLocation().getWorld().getName());
                        Block block = world.getBlockAt(player.getLocation().getBlockX(),player.getLocation().getBlockY(),player.getLocation().getBlockZ());
                        BlockState blockState = block.getState();
                        blockState.setType(Material.valueOf("GOLD_PLATE"));
                        block.setType(Material.valueOf("GOLD_PLATE"));
                        hd.createhologram(player.getLocation(),"§e踩压力板开始跑酷","§b雪枫之都跑酷起点");
                        player.sendMessage("§a你已经成功设置起点");
                        start=true;
                        break;
                    case"past":
                        if(start){
                            size++;
                            Dataload.createlocation("pastlocation"+size,player.getLocation());
                            World world2 = Bukkit.getWorld(player.getLocation().getWorld().getName());
                            Block block2 = world2.getBlockAt(player.getLocation().getBlockX(),player.getLocation().getBlockY(),player.getLocation().getBlockZ());
                            BlockState blockState2 = block2.getState();
                            blockState2.setType(Material.valueOf("GOLD_PLATE"));
                            block2.setType(Material.valueOf("GOLD_PLATE"));
                            hd.createhologram(player.getLocation(),"§a踩压压力板来记录","§b跑酷途经点"+size);
                            player.sendMessage("§a你已经成功设置经过点"+size);
                            if(!past){
                                past=true;
                            }
                            break;
                        }else {
                            player.sendMessage("§c请先设置起点");
                            break;
                        }
                    case"end":
                        if(past){
                            Dataload.createlocation("endlocation",player.getLocation());
                            World world3 = Bukkit.getWorld(player.getLocation().getWorld().getName());
                            Block block3 = world3.getBlockAt(player.getLocation().getBlockX(),player.getLocation().getBlockY(),player.getLocation().getBlockZ());
                            BlockState blockState3 = block3.getState();
                            blockState3.setType(Material.valueOf("GOLD_PLATE"));
                            block3.setType(Material.valueOf("GOLD_PLATE"));
                            hd.createhologram(player.getLocation(),"§a恭喜您完成跑酷！","§b雪枫之都跑酷终点");
                            player.sendMessage("§a你已经成功设置终点");
                            size=0;
                            break;
                        }else {
                            player.sendMessage("§c请先设置记录点！");
                            break;
                        }
                }
            }else {
                player.sendMessage("§a输入/pkset start设置起点");
                player.sendMessage("§a输入/pkset past设置经过点");
                player.sendMessage("§a输入/pkset end设置终点");
            }

        }else {
            System.out.println("§4该指令只能玩家输入");
        }
        return false;

    }
    public static int getsize(){
        return size;
    }

}
