package nl.dizmizzer.core.handler;

import net.hypixel.api.reply.GuildReply;

public interface WhitelistHandler {

    void handle(GuildReply.Guild.Member guildMember);
}
