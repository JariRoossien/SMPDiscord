package nl.dizmizzer.discord.commands;

import java.util.List;

public interface DCommand {

    String getName();

    String getDescription();

    List<DCommandInput> getVariables();

    List<DSubCommand> getSubCommands();
}
