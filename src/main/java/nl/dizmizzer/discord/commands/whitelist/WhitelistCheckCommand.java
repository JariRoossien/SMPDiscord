package nl.dizmizzer.discord.commands.whitelist;

import nl.dizmizzer.discord.commands.DCommandInput;
import nl.dizmizzer.discord.commands.DSubCommand;

import java.util.ArrayList;
import java.util.List;

public class WhitelistCheckCommand implements DSubCommand {

    @Override
    public String getName() {
        return "check";
    }

    @Override
    public String getDescription() {
        return "Checks if a player is whitelisted.";
    }

    @Override
    public List<DCommandInput> getOptions() {
        final ArrayList<DCommandInput> dCommandInputs = new ArrayList<>();
        dCommandInputs.add(DCommandInput.getNew("uuid", false));
        dCommandInputs.add(DCommandInput.getNew("username", false));
        return dCommandInputs;
    }
}
