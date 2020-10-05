package me.snownetwork.parkour.Command;

import me.snownetwork.parkour.Check.Check;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Checkgame implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Check.checkgame();//检查游戏完整性
        return false;
    }
}
