package nl.dizmizzer;

import nl.dizmizzer.discord.DiscordModule;
import nl.dizmizzer.discord.handler.BasicMessageHandler;

public class Main {
    public static void main(String[] args) {
        DiscordModule module = new DiscordModule();
        module.getHandlerManager().registerHandler(new BasicMessageHandler());
        module.getStatusManager().setPlayerCount(0);
    }

}
