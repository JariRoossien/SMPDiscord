package nl.dizmizzer.core.entity;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public interface Command {

    void run(SlashCommandInteractionEvent event);

    String getCommand();

    String[] getOptions();
}
