package nl.dizmizzer.core.runnable;

import nl.dizmizzer.core.entity.WhitelistPlayer;
import nl.dizmizzer.core.manager.MemberHandlerManager;
import nl.dizmizzer.core.provider.HypixelAPIProvider;
import nl.dizmizzer.core.provider.MojangAPI;

import java.util.logging.Logger;

public class GetGuildPlayerListRunnable implements Runnable {

    private final HypixelAPIProvider hypixelAPIProvider;
    private final MemberHandlerManager handlerManager;

    public GetGuildPlayerListRunnable(HypixelAPIProvider hypixelAPIProvider, MemberHandlerManager handlerManager) {
        this.hypixelAPIProvider = hypixelAPIProvider;
        this.handlerManager = handlerManager;
    }

    @Override
    public void run() {
        Logger.getLogger(getClass().getName()).info("Requesting Whitelist...");
        hypixelAPIProvider.getHypixelAPI().getGuildById("610ff0de8ea8c94ed6e2ef46")
                .whenComplete(
                        (success, throwable) -> {
                            Logger.getLogger(getClass().getName()).info("Found " + success.getGuild().getMembers().size() + " members for " + success.getGuild().getName());
                            success.getGuild().getMembers().forEach(
                                    member -> {
                                        String username = MojangAPI.getUsername(member.getUuid().toString());
                                        handlerManager.addPlayerToWhitelist(new WhitelistPlayer(member.getUuid(), username, "610ff0de8ea8c94ed6e2ef46", true));
                                    }
                            );
                        });
        hypixelAPIProvider.getHypixelAPI().getGuildById("565ef3cf0cf23f81e9aecdb9")
                .whenComplete(
                        (success, throwable) -> {
                            Logger.getLogger(getClass().getName()).info("Found " + success.getGuild().getMembers().size() + " members for " + success.getGuild().getName());
                            success.getGuild().getMembers().forEach(
                                    member -> {
                                        String username = MojangAPI.getUsername(member.getUuid().toString());
                                        handlerManager.addPlayerToWhitelist(new WhitelistPlayer(member.getUuid(), username, "565ef3cf0cf23f81e9aecdb9", true));
                                    });
                        });
    }
}
