package nl.dizmizzer;

import nl.dizmizzer.core.handler.GuildWhitelistRepositoryHandler;
import nl.dizmizzer.core.manager.MemberHandlerManager;
import nl.dizmizzer.core.provider.HypixelAPIProvider;
import nl.dizmizzer.core.repository.BasicWhitelistRepository;
import nl.dizmizzer.core.repository.WhitelistRepository;
import nl.dizmizzer.core.runnable.GetGuildPlayerListRunnable;
import nl.dizmizzer.discord.DiscordModule;
import nl.dizmizzer.discord.config.PropertiesConfigManager;
import nl.dizmizzer.discord.handler.BasicDiscordMessageHandler;

import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        MemberHandlerManager memberHandlerManager = new MemberHandlerManager();
        WhitelistRepository whitelistRepository = new BasicWhitelistRepository();

        memberHandlerManager.registerHandler(new GuildWhitelistRepositoryHandler(whitelistRepository));

        DiscordModule module = new DiscordModule(new PropertiesConfigManager());
        final UUID apiKey = UUID.fromString(module.getConfigManager().getStringFrom("api.key"));
        HypixelAPIProvider hypixelAPIProvider = new HypixelAPIProvider(apiKey);
        new GetGuildPlayerListRunnable(hypixelAPIProvider, memberHandlerManager).run();
        module.getHandlerManager().registerHandler(new BasicDiscordMessageHandler());
        module.getStatusManager().setPlayerCount(0);
    }

}
