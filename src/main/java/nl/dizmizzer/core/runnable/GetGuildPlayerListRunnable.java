package nl.dizmizzer.core.runnable;

import nl.dizmizzer.core.manager.MemberHandlerManager;
import nl.dizmizzer.core.provider.HypixelAPIProvider;

public class GetGuildPlayerListRunnable implements Runnable {

    private final HypixelAPIProvider hypixelAPIProvider;
    private final MemberHandlerManager handlerManager;

    public GetGuildPlayerListRunnable(HypixelAPIProvider hypixelAPIProvider, MemberHandlerManager handlerManager) {
        this.hypixelAPIProvider = hypixelAPIProvider;
        this.handlerManager = handlerManager;
    }

    @Override
    public void run() {
        hypixelAPIProvider.getHypixelAPI().getGuildById("610ff0de8ea8c94ed6e2ef46")
                .whenComplete(
                        (success, throwable) -> success.getGuild().getMembers().forEach(
                                member -> handlerManager.getHandlers().forEach(
                                        handler -> handler.handle(member)
                                )
                        )
                );
    }
}
