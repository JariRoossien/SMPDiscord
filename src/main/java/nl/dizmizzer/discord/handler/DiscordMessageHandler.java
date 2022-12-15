package nl.dizmizzer.discord.handler;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;

public interface DiscordMessageHandler {

    void handleIncomingMessage(Member member, Message message);
}
