package io.github.robertoccu.commandblockhelper;

import java.util.Set;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {
  private static final String MESSAGE_COMMAND_SET = FORMAT("&2Command set: &7");
  private static final String MESSAGE_NAME_SET = FORMAT("&2Name set: &7");
  private static final String MESSAGE_GET_COMMAND = FORMAT("&6Command Block command: &7");
  private static final String MESSAGE_GET_NAME = FORMAT("&6Command Block name: &7");
  private static final String MESSAGE_SENDER_NOT_PLAYER = FORMAT("&cSender must be a player.");
  private static final String MESSAGE_NOT_COMMAND_BLOCK = FORMAT("&cYou are not looking at a Command Block.");
  private static final String MESSAGE_ERROR_PERMISSIONS = FORMAT("&cYou don't have permissions to use this command.");

  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if ((sender instanceof Player)) {
      if (sender.hasPermission("cmdblockhelper.edit")) {
        Player player = (Player)sender;
        Block block = player.getTargetBlock((Set) null, 5);
        BlockState blockState = block.getState();
        if (!blockState.getType().equals(Material.COMMAND_BLOCK)
            && !blockState.getType().equals(Material.CHAIN_COMMAND_BLOCK)
            && !blockState.getType().equals(Material.REPEATING_COMMAND_BLOCK)) {
          sender.sendMessage(MESSAGE_NOT_COMMAND_BLOCK);
          return false;
        }
        switch (cmd.getName()) {
          case "cbset":
            sender.sendMessage(MESSAGE_COMMAND_SET +
                Logic.setCBCommand(blockState, String.join(" ", args), player));
            return true;
          case "cbadd":
            sender.sendMessage(MESSAGE_COMMAND_SET +
                Logic.addToCBCommand(blockState, String.join(" ", args), player));
            return true;
          case "cbget":
            sender.sendMessage(MESSAGE_GET_COMMAND + Logic.getCBCommand(blockState));
            return true;
          case "cbname":
            sender.sendMessage(MESSAGE_GET_NAME + Logic.getCBName(blockState));
            return true;
          case "cbrename":
            sender.sendMessage(MESSAGE_NAME_SET + Logic.setCBName(blockState,
                String.join(" ", args)));
            return true;
        }
        return false;
      }
      sender.sendMessage(MESSAGE_ERROR_PERMISSIONS);
    }
    else {
      sender.sendMessage(MESSAGE_SENDER_NOT_PLAYER);
    }
    return false;
  }

  private static final String FORMAT(String string) {
    return ChatColor.translateAlternateColorCodes('&', string);
  }
}
