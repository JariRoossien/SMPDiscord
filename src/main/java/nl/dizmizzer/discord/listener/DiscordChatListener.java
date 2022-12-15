package nl.dizmizzer.discord.listener;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import nl.dizmizzer.discord.config.ConfigManager;
import nl.dizmizzer.discord.handler.DiscordMessageHandler;
import nl.dizmizzer.discord.manager.HandlerManager;
import org.jetbrains.annotations.NotNull;

public class DiscordChatListener extends ListenerAdapter {

    private String chatId = "-1";
    private final HandlerManager handlerManager;

    public DiscordChatListener(ConfigManager manager, HandlerManager handlerManager) {
        chatId = manager.getStringFrom("message.channel");
        this.handlerManager = handlerManager;
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (!event.getChannel().getId().equals(chatId)) return;
        if (event.getMember() == null) return;
        if (event.getMember().getIdLong() == event.getJDA().getSelfUser().getIdLong()) return;
        handlerManager.getHandlers().forEach(handler -> {
            handler.handleIncomingMessage(event.getMember(), event.getMessage());
        });
    }
}
