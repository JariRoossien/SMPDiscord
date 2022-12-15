package nl.dizmizzer.discord.config;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
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
    }

    @Override
    public boolean save() {
        return true;
    }
}
