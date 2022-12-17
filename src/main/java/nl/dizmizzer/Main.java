package nl.dizmizzer;

import nl.dizmizzer.core.CoreModule;
import nl.dizmizzer.core.runnable.GetGuildPlayerListRunnable;
import nl.dizmizzer.discord.DiscordModule;
import nl.dizmizzer.discord.handler.BasicDiscordMessageHandler;

import java.util.Timer;
import java.util.TimerTask;

public class Main {

    public static void main(String[] args) {
        try {
            CoreModule coreModule = new CoreModule();
            DiscordModule module = new DiscordModule(coreModule.getConfigManager(), coreModule.getMemberHandlerManager(), coreModule.getWhitelistRepository());

            new GetGuildPlayerListRunnable(coreModule.getHypixelAPIProvider(), coreModule.getMemberHandlerManager()).run();
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    coreModule.getWhitelistRepository().save();
                }
            }, 10000L);

            module.getHandlerManager().registerHandler(new BasicDiscordMessageHandler());
            module.getStatusManager().setPlayerCount(0);
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("Program exited...");
        }
    }

}
