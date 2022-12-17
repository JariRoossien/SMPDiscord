package nl.dizmizzer.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import nl.dizmizzer.core.config.ConfigManager;
import nl.dizmizzer.core.config.PropertiesConfigManager;
import nl.dizmizzer.core.handler.GuildWhitelistRepositoryHandler;
import nl.dizmizzer.core.manager.MemberHandlerManager;
import nl.dizmizzer.core.provider.HypixelAPIProvider;
import nl.dizmizzer.core.repository.JsonWhitelistRepository;
import nl.dizmizzer.core.repository.WhitelistRepository;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CoreModule {

    private final WhitelistRepository whitelistRepository;
    private final HypixelAPIProvider hypixelAPIProvider;
    private final MemberHandlerManager memberHandlerManager;
    private final ConfigManager configManager;

    public CoreModule() {
        this.configManager = new PropertiesConfigManager();
        this.configManager.load();
        this.whitelistRepository = new JsonWhitelistRepository();
        whitelistRepository.load();
        this.memberHandlerManager = new MemberHandlerManager();

        UUID apiKey = UUID.fromString(configManager.getStringFrom("api.key"));
        this.hypixelAPIProvider = new HypixelAPIProvider(apiKey);

        this.memberHandlerManager.registerHandler(new GuildWhitelistRepositoryHandler(this.whitelistRepository));
    }

    public void destroy() {
        this.configManager.save();
        this.whitelistRepository.save();
    }
}
