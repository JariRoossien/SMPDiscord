package nl.dizmizzer.mc;

import nl.dizmizzer.discord.DiscordModule;
import nl.dizmizzer.mc.handler.DiscordMCMessageHandler;
import nl.dizmizzer.mc.listener.PlayerChatListener;
import nl.dizmizzer.mc.listener.PlayerJoinLeaveListener;
import org.bukkit.plugin.java.JavaPlugin;

public class MCModule extends JavaPlugin {

    private DiscordModule discordModule;

    @Override
    public void onEnable() {
        this.discordModule = new DiscordModule();
        this.discordModule.getHandlerManager().registerHandler(new DiscordMCMessageHandler(getServer()));
        getServer().getPluginManager().registerEvents(
                new PlayerJoinLeaveListener(discordModule.getStatusManager(), discordModule.getDiscordChatManager(), getServer()), this);
        getServer().getPluginManager().registerEvents(
                new PlayerChatListener(discordModule.getDiscordChatManager()), this);
        discordModule.getStatusManager().setPlayerCount(0);
    }

    @Override
    public void onDisable() {
        discordModule.destroy();
    }
}
