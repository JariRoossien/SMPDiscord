package nl.dizmizzer.core.manager;

import lombok.Getter;
import nl.dizmizzer.core.handler.WhitelistHandler;

import java.util.HashSet;
import java.util.Set;

public class MemberHandlerManager {

    @Getter private final Set<WhitelistHandler> handlers = new HashSet<>();

    public void registerHandler(WhitelistHandler handler) {
        handlers.add(handler);
    }

}
