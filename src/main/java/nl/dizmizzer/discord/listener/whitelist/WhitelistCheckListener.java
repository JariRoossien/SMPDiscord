package nl.dizmizzer.discord.listener.whitelist;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import nl.dizmizzer.core.entity.WhitelistPlayer;
import nl.dizmizzer.core.repository.WhitelistRepository;
import nl.dizmizzer.discord.listener.SubCommandHandler;

import java.util.UUID;

public class WhitelistCheckListener implements SubCommandHandler {

    private final WhitelistRepository whitelistRepository;

    public WhitelistCheckListener(WhitelistRepository whitelistRepository) {
        this.whitelistRepository = whitelistRepository;
    }

    @Override
    public void handleCommand(SlashCommandInteractionEvent event) {
        if (event.getOption("uuid") == null && event.getOption("username") == null)
            throw new RuntimeException("No option given.");

        WhitelistPlayer player;
        if (event.getOption("username") != null) {
            player = whitelistRepository.getPlayerFromUsername(event.getOption("username").getAsString());
        } else {
            String uuidString = event.getOption("uuid").getAsString();
            if (!WhitelistAddListener.isValidUUID(uuidString)) throw new RuntimeException("Not a valid UUID");
            UUID uuid = UUID.fromString(uuidString);
            player = whitelistRepository.getPlayerFromUUID(uuid);
        }

        if (player == null) {
            throw new RuntimeException("Player is not whitelisted or does not exist!");
        }

        event.reply("""
                **Username**: `%s`
                **UUID**: `%s`
                **Referral**: `%s`
                **Invited through guild:** `%s`
                """.formatted(
                        player.getPlayerUsername(),
                player.getPlayerId(),
                player.getReferral(),
                (player.isInsertedByGuild()) ? "true" : "false")).setEphemeral(true).queue();
    }
}
