package nl.dizmizzer.discord.commands;

import java.util.ArrayList;
import java.util.List;

public class DeepDTestCommand implements DCommand {
    @Override
    public String getName() {
        return "testname";
    }

    @Override
    public String getDescription() {
        return "Deep Test Description";
    }

    @Override
    public List<DCommandInput> getVariables() {
        return null;
    }

    @Override
    public List<DSubCommand> getSubCommands() {
        final ArrayList<DSubCommand> dSubCommands = new ArrayList<>();
        dSubCommands.add(new DSubCommand() {
            @Override
            public String getName() {
                return "testsubcommand";
            }

            @Override
            public String getDescription() {
                return "Testing SubCommand";
            }

            @Override
            public List<DCommandInput> getOptions() {
                return new ArrayList<>();
            }
        });
        return dSubCommands;
    }
}
