package nl.dizmizzer.discord.listener.whitelist;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import nl.dizmizzer.core.entity.WhitelistPlayer;
import nl.dizmizzer.core.manager.MemberHandlerManager;
import nl.dizmizzer.core.provider.MojangAPI;
import nl.dizmizzer.discord.listener.SubCommandHandler;

import java.util.UUID;

public class WhitelistAddListener implements SubCommandHandler {

    private final MemberHandlerManager memberHandlerManager;
    private final static long STAFF_ROLE_ID = 984858305732161568L;
    public WhitelistAddListener(MemberHandlerManager memberHandlerManager) {
        this.memberHandlerManager = memberHandlerManager;
    }

    public void handleCommand(SlashCommandInteractionEvent event) {
        if (event.getMember().getRoles().stream().noneMatch(role -> role.getIdLong() == STAFF_ROLE_ID)) throw new RuntimeException("No permission!");
        if (event.getOption("uuid") == null && event.getOption("username") == null) throw new RuntimeException("No option given.");
        if (event.getOption("referral") == null) throw new RuntimeException("Referral not found.");
        String referral = event.getOption("referral").getAsString();
        UUID uuid = null;
        String username = null;

        if (event.getOption("uuid") != null) {
            String uuidString = event.getOption("uuid").getAsString();
            if (!isValidUUID(uuidString)) throw new RuntimeException("Given UUID is not valid.");
            uuid = UUID.fromString(uuidString);
            username = MojangAPI.getUsername(uuidString);
            if (username == null) throw new RuntimeException("Player does not exist!");
        }

        if (event.getOption("username") != null) {
            username = event.getOption("username").getAsString();
            uuid = MojangAPI.getUUID(username);
            if (uuid == null) throw new RuntimeException("Player does not exist!");
        }

        memberHandlerManager.addPlayerToWhitelist(new WhitelistPlayer(uuid, username, referral, false));
        event.reply("Succesfully added `" + username + "` to the whitelist!").queue();
    }

    public static boolean isValidUUID(String uuid) {
        try {
            UUID.fromString(uuid);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
