package nl.dizmizzer.discord.handler;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;

import java.util.logging.Logger;

public class BasicMessageHandler implements DiscordMessageHandler {

    @Override
    public void handleIncomingMessage(Member member, Message message) {
        Logger.getLogger(getClass().getName()).info(member.getEffectiveName() + ": " + message.getContentRaw());
    }
}
