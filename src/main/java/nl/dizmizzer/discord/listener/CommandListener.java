package nl.dizmizzer.discord.listener;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

public class CommandListener extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        Logger.getLogger(getClass().getName()).info("Name: " + event.getInteraction().getName());
        Logger.getLogger(getClass().getName()).info("Id: " + event.getInteraction().getId());
        Logger.getLogger(getClass().getName()).info("Command-Id: " + event.getInteraction().getCommandId());
        Logger.getLogger(getClass().getName()).info("Command-String: " + event.getInteraction().getCommandString());
    }
}
