package nl.dizmizzer.mc.listener;

import nl.dizmizzer.discord.manager.DiscordChatManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatListener implements Listener {

    private final DiscordChatManager chatManager;

    public PlayerChatListener(DiscordChatManager chatManager) {
        this.chatManager = chatManager;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent playerChatEvent) {
        Player player = playerChatEvent.getPlayer();
        String message = playerChatEvent.getMessage();
        chatManager.sendPlayerMessage(player.getPlayerListName(), sanitizeString(message));
    }

    @EventHandler
    public void onChatFormatter(AsyncPlayerChatEvent playerChatEvent) {
        playerChatEvent.setFormat("%s: %s");
    }

    private static String sanitizeString(String s) {
        String toReturn = s.replace("@", "\\@-");
        while (toReturn.contains("\\\\")) {
            toReturn = toReturn.replace("\\\\", "\\");
        }

        return toReturn;
    }
}
