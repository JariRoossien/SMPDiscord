package nl.dizmizzer.core.handler;

import net.hypixel.api.reply.GuildReply;
import nl.dizmizzer.core.entity.WhitelistPlayer;
import nl.dizmizzer.core.repository.WhitelistRepository;

import java.util.logging.Logger;

public class GuildWhitelistRepositoryHandler implements WhitelistHandler {
    private WhitelistRepository whitelistRepository;

    public GuildWhitelistRepositoryHandler(WhitelistRepository whitelistRepository) {
        this.whitelistRepository = whitelistRepository;
    }

    @Override
    public void handle(GuildReply.Guild.Member guildMember) {
        if (whitelistRepository.getPlayerFrom(guildMember.getUuid()) == null) {
            whitelistRepository.addPlayer(new WhitelistPlayer(guildMember.getUuid(), true));
            Logger.getLogger(getClass().getName()).info("Whitelisted " + guildMember.getUuid());
        }
    }
}
