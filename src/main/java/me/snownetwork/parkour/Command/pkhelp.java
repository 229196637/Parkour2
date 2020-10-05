package me.snownetwork.parkour.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class pkhelp implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(p.hasPermission("admin.snow")){
                p.sendMessage("---------§7[§b跑酷系统§7]-------------");
                p.sendMessage("§a输入/pkset来获取设置帮助");
                p.sendMessage("§a输入/pkhelp来获取帮助");

            }else {
                if(args.length>=1){
                    if(args[0].equals("2")){
                        p.sendMessage("§8---------§8[§b跑酷系统§8]-------------");
                        p.sendMessage("§b输入§6/pklast§b返回上一个记录点");
                        p.sendMessage("§b输入§6/pkrest§b重置你的跑酷");

                    }

                }else {
                    p.sendMessage("§8---------§8[§b跑酷系统§8]-------------");
                    p.sendMessage("§e您正在游玩雪枫之都跑酷");
                    p.sendMessage("§e跑酷规则：");
                    p.sendMessage("§b跑酷中不可飞行");
                    p.sendMessage("§b您需要经过每一个记录点");
                    p.sendMessage("§b如果有直接跳到终点，会直接把你传送到上一个记录点");
                    p.sendMessage("§b如果有忽视记录点1知道到记录点2，同样是没效果的");
                    p.sendMessage("§b雪枫之都运营团队祝您游戏愉快！");
                    p.sendMessage("§b输入§6/pkhelp 2§b查看下一页");
                }
            }

        }
        return false;
    }
}
