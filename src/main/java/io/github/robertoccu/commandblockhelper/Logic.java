package io.github.robertoccu.commandblockhelper;

import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.block.CommandBlock;
import org.bukkit.entity.Player;

public class Logic {
  public static String setCBCommand(BlockState cmdBlock, String command, Player player) {
    ((CommandBlock)cmdBlock).setCommand(command);
    setCBName(cmdBlock, player.getName() + "{" + cmdBlock.getX() + "," + cmdBlock
        .getY() + "," + cmdBlock.getZ() + "}");

    cmdBlock.update();
    return ((CommandBlock)cmdBlock).getCommand();
  }

  public static String addToCBCommand(BlockState cmdBlock, String command, Player player) {
    String oldCommand = ((CommandBlock)cmdBlock).getCommand();
    String newCommand = oldCommand + " " + command;

    cmdBlock.update();
    return setCBCommand(cmdBlock, newCommand, player);
  }

  public static String getCBCommand(BlockState cmdBlock) {
    return ((CommandBlock)cmdBlock).getCommand();
  }

  public static String getCBName(BlockState cmdBlock) {
    return ((CommandBlock)cmdBlock).getName();
  }

  public static String setCBName(BlockState cmdBlock, String name) {
    ((CommandBlock)cmdBlock).setName(name);

    cmdBlock.update();
    return ((CommandBlock)cmdBlock).getName();
  }
}
