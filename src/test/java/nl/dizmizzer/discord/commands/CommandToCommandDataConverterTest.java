package nl.dizmizzer.discord.commands;

import junit.framework.TestCase;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import org.junit.Assert;

public class CommandToCommandDataConverterTest extends TestCase {

    public void testConvertForSimpleCommand() {
        DCommand simpleCommand = new SimpleDTestCommand();
        SlashCommandData data = CommandToCommandDataConverter.convert(simpleCommand);
        Assert.assertEquals(simpleCommand.getName(), data.getName());
        Assert.assertEquals(simpleCommand.getDescription(), data.getDescription());

        Assert.assertEquals(0, data.getSubcommands().size());
        Assert.assertEquals(0, data.getSubcommandGroups().size());
        Assert.assertEquals(2, data.getOptions().size());

        DCommandInput dSubCommand = simpleCommand.getVariables().get(0);
        Assert.assertEquals(dSubCommand.getInputName(), data.getOptions().get(0).getName());
        Assert.assertEquals(dSubCommand.isRequired(), data.getOptions().get(0).isRequired());
    }

    public void testDeepCommand() {
        DCommand deepCommand = new DeepDTestCommand();
        SlashCommandData data = CommandToCommandDataConverter.convert(deepCommand);
        Assert.assertEquals(deepCommand.getName(), data.getName());
        Assert.assertEquals(deepCommand.getDescription(), data.getDescription());

        Assert.assertEquals(1, data.getSubcommands().size());
        Assert.assertEquals(0, data.getSubcommandGroups().size());
        Assert.assertEquals(0, data.getOptions().size());

        SubcommandData subCommandData = data.getSubcommands().get(0);
        DSubCommand subCommand = deepCommand.getSubCommands().get(0);

        Assert.assertEquals(subCommand.getName(), subCommandData.getName());
        Assert.assertEquals(subCommand.getDescription(), subCommandData.getDescription());
        Assert.assertEquals(subCommand.getOptions().size(), subCommandData.getOptions().size());
    }
}
