package me.snownetwork.parkour.Command;

import me.snownetwork.parkour.Data.Dataload;
import me.snownetwork.parkour.game.GameGather;
import me.snownetwork.parkour.game.gamelisten;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class pkreset implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(GameGather.getInparkour().contains(p.getUniqueId())){
                gamelisten.reSetPlayer(p.getUniqueId());
                p.teleport((Location) Dataload.getdata().get("startlocation"));
            }else {
                p.sendMessage("§f[§b跑酷系统§f]:§4您不在跑酷中！请勿使用！");
            }

        }
        return false;
    }
}
