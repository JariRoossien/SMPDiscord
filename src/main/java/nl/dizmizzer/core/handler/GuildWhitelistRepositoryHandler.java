package nl.dizmizzer.core.handler;

import nl.dizmizzer.core.entity.WhitelistPlayer;
import nl.dizmizzer.core.repository.WhitelistRepository;

public class GuildWhitelistRepositoryHandler implements WhitelistHandler {
    private final WhitelistRepository whitelistRepository;

    public GuildWhitelistRepositoryHandler(WhitelistRepository whitelistRepository) {
        this.whitelistRepository = whitelistRepository;
    }

    @Override
    public void handle(WhitelistPlayer player) {
        if (whitelistRepository.getPlayerFromUUID(player.getPlayerId()) == null) {
            whitelistRepository.addPlayer(player);
        }
    }
}
