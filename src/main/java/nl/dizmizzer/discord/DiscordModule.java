package nl.dizmizzer.discord;

import lombok.Getter;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import nl.dizmizzer.core.config.BasicConfigManager;
import nl.dizmizzer.core.config.ConfigManager;
import nl.dizmizzer.core.manager.MemberHandlerManager;
import nl.dizmizzer.core.repository.BasicWhitelistRepository;
import nl.dizmizzer.core.repository.WhitelistRepository;
import nl.dizmizzer.discord.commands.CommandToCommandDataConverter;
import nl.dizmizzer.discord.commands.whitelist.WhitelistCommand;
import nl.dizmizzer.discord.listener.CommandListener;
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
        this(new BasicConfigManager(), new MemberHandlerManager(), new BasicWhitelistRepository());
    }

    public DiscordModule(ConfigManager configManager, MemberHandlerManager memberHandlerManager, WhitelistRepository whitelistRepository) {
        this.configManager = configManager;
        this.jdaProvider = new JDAProvider(configManager);

        this.handlerManager = new HandlerManager();
        this.statusManager = new StatusManager(jdaProvider);

        TextChannel channel = jdaProvider.getJda().getTextChannelById(configManager.getStringFrom("message.channel"));
        this.discordChatManager = new DiscordChatManager(channel);
        this.jdaProvider.getJda().addEventListener(new DiscordChatListener(configManager, handlerManager));
        this.jdaProvider.getJda().addEventListener(new CommandListener(memberHandlerManager, whitelistRepository));

        WhitelistCommand whitelistCommand = new WhitelistCommand();
        CommandData commandData = CommandToCommandDataConverter.convert(whitelistCommand);

        this.getJdaProvider().getJda().getGuildById("917497317270093834").upsertCommand(commandData).queue();
    }

    public void destroy() {
        this.jdaProvider.disconnect();
    }
}
