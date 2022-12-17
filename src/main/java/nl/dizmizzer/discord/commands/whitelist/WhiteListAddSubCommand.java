package nl.dizmizzer.discord.commands.whitelist;

import nl.dizmizzer.discord.commands.DCommandInput;
import nl.dizmizzer.discord.commands.DSubCommand;

import java.util.ArrayList;
import java.util.List;

public class WhiteListAddSubCommand implements DSubCommand {

    private final List<DCommandInput> variables;

    public WhiteListAddSubCommand() {
        this.variables = new ArrayList<>();
        this.variables.add(DCommandInput.getNew("referral", true));
        this.variables.add(DCommandInput.getNew("uuid", false));
        this.variables.add(DCommandInput.getNew("username", false));
    }

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getDescription() {
        return "Whitelist A user's UUID";
    }

    @Override
    public List<DCommandInput> getOptions() {
        return variables;
    }
}
