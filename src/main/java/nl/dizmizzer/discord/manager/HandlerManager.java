package nl.dizmizzer.discord.manager;

import lombok.Getter;
import nl.dizmizzer.discord.handler.DiscordMessageHandler;

import java.util.ArrayList;
import java.util.List;

public class HandlerManager {

    @Getter
    private final List<DiscordMessageHandler> handlers = new ArrayList<>();

    public HandlerManager() {
    }

    public void registerHandler(DiscordMessageHandler handler) {
        this.handlers.add(handler);
    }

}
