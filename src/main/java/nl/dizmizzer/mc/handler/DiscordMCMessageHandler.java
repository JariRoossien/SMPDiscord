package nl.dizmizzer.mc.handler;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import nl.dizmizzer.discord.handler.DiscordMessageHandler;
import org.bukkit.ChatColor;
import org.bukkit.Server;

public class DiscordMCMessageHandler implements DiscordMessageHandler {

    private final Server server;

    public DiscordMCMessageHandler(Server server) {
        this.server = server;
    }

    @Override
    public void handleIncomingMessage(Member member, Message message) {
        String formattedMessage = ChatColor.BLUE + "[D] " + ChatColor.RESET + "%s: %s";
        server.getOnlinePlayers().forEach(player ->
                player.sendMessage(formattedMessage.formatted(member.getEffectiveName(), message.getContentRaw()))
        );
        server.getLogger().info("[D] " + member.getEffectiveName() + ": " + message.getContentRaw());
    }
}
