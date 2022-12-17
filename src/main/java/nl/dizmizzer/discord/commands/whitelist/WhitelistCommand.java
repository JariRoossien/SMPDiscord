package nl.dizmizzer.discord.commands.whitelist;

import nl.dizmizzer.discord.commands.DCommand;
import nl.dizmizzer.discord.commands.DCommandInput;
import nl.dizmizzer.discord.commands.DSubCommand;

import java.util.ArrayList;
import java.util.List;

public class WhitelistCommand implements DCommand {

    private final List<DCommandInput> variables;
    private final List<DSubCommand> subCommands;

    public WhitelistCommand() {
        this.variables = new ArrayList<>();

        this.subCommands = new ArrayList<>();
        this.subCommands.add(new WhiteListAddSubCommand());
        this.subCommands.add(new WhitelistCheckCommand());
    }

    @Override
    public String getName() {
        return "whitelist";
    }

    @Override
    public String getDescription() {
        return "Check if someone is whitelisted.";
    }

    @Override
    public List<DCommandInput> getVariables() {
        return variables;
    }

    @Override
    public List<DSubCommand> getSubCommands() {
        return subCommands;
    }
}
