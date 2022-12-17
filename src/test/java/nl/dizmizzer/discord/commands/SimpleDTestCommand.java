package nl.dizmizzer.discord.commands;

import java.util.ArrayList;
import java.util.List;

public class SimpleDTestCommand implements DCommand {

    public SimpleDTestCommand() {
    }
    @Override
    public String getName() {
        return "testname";
    }

    @Override
    public String getDescription() {
        return "Test Description";
    }

    @Override
    public List<DCommandInput> getVariables() {
        final ArrayList<DCommandInput> dCommandInputs = new ArrayList<>();
        dCommandInputs.add(DCommandInput.getNew("inputa", false));
        dCommandInputs.add(DCommandInput.getNew("inputb", true));
        return dCommandInputs;
    }

    @Override
    public List<DSubCommand> getSubCommands() {
        return null;
    }
}
