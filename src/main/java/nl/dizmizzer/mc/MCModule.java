package nl.dizmizzer.mc;

import nl.dizmizzer.core.CoreModule;
import nl.dizmizzer.core.runnable.GetGuildPlayerListRunnable;
import nl.dizmizzer.discord.DiscordModule;
import nl.dizmizzer.mc.handler.DiscordMCMessageHandler;
import nl.dizmizzer.mc.handler.MemberWhitelistHandler;
import nl.dizmizzer.mc.listener.PlayerChatListener;
import nl.dizmizzer.mc.listener.PlayerJoinLeaveListener;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class MCModule extends JavaPlugin {

    private DiscordModule discordModule;
    private CoreModule coreModule;
    @Override
    public void onEnable() {
        this.coreModule = new CoreModule();
        this.discordModule = new DiscordModule(coreModule.getConfigManager(), coreModule.getMemberHandlerManager(), coreModule.getWhitelistRepository());

        this.discordModule.getHandlerManager().registerHandler(new DiscordMCMessageHandler(getServer()));
        this.coreModule.getMemberHandlerManager().registerHandler(new MemberWhitelistHandler(getServer()));

        registerEvents(
                new PlayerJoinLeaveListener(
                        discordModule.getStatusManager(),
                        discordModule.getDiscordChatManager(),
                        getServer()),
                new PlayerChatListener(discordModule.getDiscordChatManager())
        );

        discordModule.getStatusManager().setPlayerCount(0);

        this.coreModule.getWhitelistRepository().getAllPlayers()
                .forEach(player -> getServer().getOfflinePlayer(player.getPlayerId()).setWhitelisted(true));

        getServer().getScheduler().runTaskTimerAsynchronously(this,
                new GetGuildPlayerListRunnable(coreModule.getHypixelAPIProvider(), coreModule.getMemberHandlerManager()),
                0,
                20 * 60 * 30);
    }

    private void registerEvents(Listener... listeners) {
        for (Listener l : listeners) {
            getServer().getPluginManager().registerEvents(l, this);
        }
    }

    @Override
    public void onDisable() {
        discordModule.destroy();
        coreModule.destroy();
    }
}
