package nl.dizmizzer.discord.listener;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public interface SubCommandHandler {
    void handleCommand(SlashCommandInteractionEvent event);
}
