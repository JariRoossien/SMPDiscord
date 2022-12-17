package nl.dizmizzer.discord.commands;

import java.util.List;

public interface DSubCommand {

    String getName();

    String getDescription();

    List<DCommandInput> getOptions();
}
