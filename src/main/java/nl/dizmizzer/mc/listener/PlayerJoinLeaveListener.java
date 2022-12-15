package nl.dizmizzer.mc.listener;

import net.dv8tion.jda.api.EmbedBuilder;
import nl.dizmizzer.discord.manager.DiscordChatManager;
import nl.dizmizzer.discord.manager.StatusManager;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoinLeaveListener implements Listener {

    private final StatusManager statusManager;
    private final DiscordChatManager chatManager;

    private final Server server;

    public PlayerJoinLeaveListener(StatusManager statusManager, DiscordChatManager chatManager, Server server) {
        this.statusManager = statusManager;
        this.chatManager = chatManager;
        this.server = server;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent joinEvent) {
        this.statusManager.setPlayerCount(server.getOnlinePlayers().size());
        Player player = joinEvent.getPlayer();
        EmbedBuilder builder = new EmbedBuilder();
        builder.setAuthor(player.getName() + " has joined the server!",
                "https://crafatar.com/avatars/" + player.getUniqueId() + "?overlay",
                "https://crafatar.com/avatars/" + player.getUniqueId() + "?overlay");
        chatManager.sendEmbed(builder.build());
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent quitEvent) {
        this.statusManager.setPlayerCount(server.getOnlinePlayers().size() - 1);
        EmbedBuilder builder = new EmbedBuilder();
        Player player = quitEvent.getPlayer();
        builder.setAuthor(player.getName() + " has left the server!",
                "https://crafatar.com/avatars/" + player.getUniqueId() + "?overlay",
                "https://crafatar.com/avatars/" + player.getUniqueId() + "?overlay");
        chatManager.sendEmbed(builder.build());

    }
}
