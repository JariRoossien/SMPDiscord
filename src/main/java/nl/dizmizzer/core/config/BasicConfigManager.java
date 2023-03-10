package nl.dizmizzer.core.config;

import java.util.HashMap;
import java.util.Map;

public class BasicConfigManager implements ConfigManager {

    private final Map<String, String> kvMap = new HashMap<>();

    @Override
    public String getStringFrom(String key) {
        return kvMap.getOrDefault(key, "");
    }

    @Override
    public boolean hasKey(String key) {
        return kvMap.containsKey(key);
    }

    @Override
    public void load() {
        kvMap.put("discord.token", System.getenv("SMP_DISCORD_TOKEN"));
        kvMap.put("message.channel", "1043579529899286528");
        kvMap.put("api.key", System.getenv("HYPIXEL_API_KEY"));
    }

    @Override
    public boolean save() {
        return true;
    }
}
