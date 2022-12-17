package nl.dizmizzer.mc.handler;

import nl.dizmizzer.core.entity.WhitelistPlayer;
import nl.dizmizzer.core.handler.WhitelistHandler;
import nl.dizmizzer.mc.MCModule;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

public class MemberWhitelistHandler implements WhitelistHandler {

    private final Server server;

    public MemberWhitelistHandler(Server server) {
        this.server = server;
    }

    @Override
    public void handle(WhitelistPlayer member) {
        OfflinePlayer player = server.getOfflinePlayer(member.getPlayerId());
        server.getScheduler().runTask(JavaPlugin.getPlugin(MCModule.class), () -> {
            if (!player.isWhitelisted())
                player.setWhitelisted(true);
        });
    }
}
