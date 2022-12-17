package nl.dizmizzer.discord.commands;

import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;

public class CommandToCommandDataConverter {

    public static SlashCommandData convert(DCommand dCommand) {
        SlashCommandData command = Commands.slash(dCommand.getName(), dCommand.getDescription());
        if (dCommand.getSubCommands() != null && dCommand.getSubCommands().size() > 0) {
            dCommand.getSubCommands().forEach(sCommand -> addSubCommand(command, sCommand));
        } else {
            dCommand.getVariables().forEach(dVariable ->
                    command.addOption(OptionType.STRING, dVariable.getInputName(), dCommand.getDescription())
            );
        }
        return command;
    }

    private static void addSubCommand(SlashCommandData commandData, DSubCommand subCommand) {
        SubcommandData subcommandData = new SubcommandData(subCommand.getName(), subCommand.getDescription());
        for (DCommandInput commandInput : subCommand.getOptions()) {
            subcommandData.addOption(OptionType.STRING, commandInput.getInputName(), commandInput.getInputName(), commandInput.isRequired());
        }
        commandData.addSubcommands(subcommandData);
    }

}
