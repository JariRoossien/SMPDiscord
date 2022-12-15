package nl.dizmizzer.discord;

import lombok.Getter;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import nl.dizmizzer.discord.config.BasicConfigManager;
import nl.dizmizzer.discord.config.ConfigManager;
import nl.dizmizzer.discord.config.PropertiesConfigManager;
import nl.dizmizzer.discord.listener.DiscordChatListener;
import nl.dizmizzer.discord.manager.DiscordChatManager;
import nl.dizmizzer.discord.manager.HandlerManager;
import nl.dizmizzer.discord.manager.StatusManager;
import nl.dizmizzer.discord.provider.JDAProvider;

public class DiscordModule {

    @Getter
    private final DiscordChatManager discordChatManager;
    @Getter
    private final JDAProvider jdaProvider;
    @Getter
    private final ConfigManager configManager;
    @Getter
    private final HandlerManager handlerManager;
    @Getter
    private final StatusManager statusManager;

    public DiscordModule() {
        this.configManager = new PropertiesConfigManager();
        configManager.load();
        this.jdaProvider = new JDAProvider(configManager);
        this.handlerManager = new HandlerManager();
        this.statusManager = new StatusManager(jdaProvider);

        TextChannel channel = jdaProvider.getJda().getTextChannelById(configManager.getStringFrom("message.channel"));
        this.discordChatManager = new DiscordChatManager(channel);

        this.jdaProvider.getJda().addEventListener(new DiscordChatListener(configManager, handlerManager));
    }

    public void destroy() {
        this.jdaProvider.disconnect();
    }
}
