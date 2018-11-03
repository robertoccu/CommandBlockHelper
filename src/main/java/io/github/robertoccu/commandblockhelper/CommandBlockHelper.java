package io.github.robertoccu.commandblockhelper;

import org.bukkit.plugin.java.JavaPlugin;

public class CommandBlockHelper extends JavaPlugin {
  public void onEnable() {
    getCommand("cbset").setExecutor(new Commands());
    getCommand("cbadd").setExecutor(new Commands());
    getCommand("cbget").setExecutor(new Commands());
    getCommand("cbname").setExecutor(new Commands());
    getCommand("cbrename").setExecutor(new Commands());

    getLogger().info("Command Block Editor Ready!");
  }

  public void onDisable() {}
}
