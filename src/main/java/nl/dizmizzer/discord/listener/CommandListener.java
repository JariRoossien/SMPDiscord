package nl.dizmizzer.discord.listener;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import nl.dizmizzer.core.manager.MemberHandlerManager;
import nl.dizmizzer.core.repository.WhitelistRepository;
import nl.dizmizzer.discord.listener.whitelist.WhitelistAddListener;
import nl.dizmizzer.discord.listener.whitelist.WhitelistCheckListener;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class CommandListener extends ListenerAdapter {

    private final Map<String, SubCommandHandler> subCommandHandlerMap = new HashMap<>();

    public CommandListener(MemberHandlerManager memberHandlerManager, WhitelistRepository whitelistRepository) {
        subCommandHandlerMap.put("add", new WhitelistAddListener(memberHandlerManager));
        subCommandHandlerMap.put("check", new WhitelistCheckListener(whitelistRepository));
    }
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (!event.getInteraction().getName().equals("whitelist")) return;
        if (event.getInteraction().getSubcommandName() == null) return;
        String subCommand = event.getInteraction().getSubcommandName();
        if (subCommandHandlerMap.containsKey(subCommand)) {
            try {
                subCommandHandlerMap.get(subCommand).handleCommand(event);
            } catch (Exception ex) {
                event.reply("**Error:** " + ex.getMessage()).setEphemeral(true).queue();
            }
        } else {
            event.reply("**Error:** Could not find subcommand.").queue();
        }
    }
}
