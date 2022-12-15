package nl.dizmizzer.discord.manager;

import net.dv8tion.jda.api.entities.Activity;
import nl.dizmizzer.discord.provider.JDAProvider;

public class StatusManager {

    private final JDAProvider jdaProvider;

    public StatusManager(JDAProvider jdaProvider) {
        this.jdaProvider = jdaProvider;
    }

    public void setPlayerCount(int count) {
        if (count == 1) {
            jdaProvider.getJda().getPresence().setActivity(Activity.playing(count + " player online."));
        } else {
            jdaProvider.getJda().getPresence().setActivity(Activity.playing(count + " players online."));
        }
    }
}
