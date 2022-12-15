package nl.dizmizzer.discord.manager;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

public class DiscordChatManager {

    private final TextChannel textChannel;

    public DiscordChatManager(TextChannel channel) {
        this.textChannel = channel;
    }

    public void sendEmbed(MessageEmbed embed) {
        if (textChannel == null) return;
        textChannel.sendMessageEmbeds(embed).queue();
    }

    public void sendPlayerMessage(String playerName, String message) {
        if (textChannel == null) return;
        textChannel.sendMessage("**%s**: %s".formatted(playerName, message)).queue();
    }
}
