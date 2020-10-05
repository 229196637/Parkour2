package me.snownetwork.parkour.Command;

import me.snownetwork.parkour.Data.Dataload;
import me.snownetwork.parkour.game.GameGather;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class pklast implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(GameGather.getInparkour().contains(p.getUniqueId())){
                int a =GameGather.getPlayerPastSize().get(p.getUniqueId())-1;
                if(GameGather.getPlayerPastSize().get(p.getUniqueId())==1){
                    p.teleport((Location)Dataload.getdata().get("startlocation"));
                }else {
                    p.teleport((Location) Dataload.getdata().get("pastlocation"+a));
                }

            }else {
                p.sendMessage("§c您不在跑酷中，请勿重复使用！");
            }

        }
        return false;
    }
}
