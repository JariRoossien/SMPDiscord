package nl.dizmizzer.mc;

import nl.dizmizzer.discord.DiscordModule;
import nl.dizmizzer.discord.config.BasicConfigManager;
import nl.dizmizzer.mc.handler.DiscordMCMessageHandler;
import nl.dizmizzer.mc.listener.PlayerChatListener;
import nl.dizmizzer.mc.listener.PlayerJoinLeaveListener;
import nl.dizmizzer.core.provider.HypixelAPIProvider;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public class MCModule extends JavaPlugin {

    private DiscordModule discordModule;
    private HypixelAPIProvider hypixelAPIProvider;

    @Override
    public void onEnable() {
        this.discordModule = new DiscordModule(new BasicConfigManager());
        this.discordModule.getHandlerManager().registerHandler(new DiscordMCMessageHandler(getServer()));
        registerEvents(
                new PlayerJoinLeaveListener(discordModule.getStatusManager(), discordModule.getDiscordChatManager(), getServer()),
                new PlayerChatListener(discordModule.getDiscordChatManager())
        );
        String apiKey = this.discordModule.getConfigManager().getStringFrom("api.key");
        this.hypixelAPIProvider = new HypixelAPIProvider(UUID.fromString(apiKey));
        discordModule.getStatusManager().setPlayerCount(0);

    }

    private void registerEvents(Listener... listeners) {
        for (Listener l : listeners) {
            getServer().getPluginManager().registerEvents(l, this);
        }
    }

    @Override
    public void onDisable() {
        discordModule.destroy();
    }
}
