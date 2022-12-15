package nl.dizmizzer.discord.provider;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import nl.dizmizzer.discord.config.ConfigManager;

public class JDAProvider {

    private final JDA jda;

    public JDAProvider(ConfigManager configManager) {
        String token = configManager.getStringFrom("discord.token");
        try {
            jda = JDABuilder.create(token,
                            GatewayIntent.GUILD_MESSAGES,
                            GatewayIntent.GUILD_VOICE_STATES,
                            GatewayIntent.GUILD_MEMBERS,
                            GatewayIntent.MESSAGE_CONTENT)
                    .build()
                    .awaitReady();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public JDA getJda() {
        return jda;
    }

    public void disconnect() {
        jda.shutdown();
    }
}
