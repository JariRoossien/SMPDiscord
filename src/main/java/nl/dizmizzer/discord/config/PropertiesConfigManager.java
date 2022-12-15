package nl.dizmizzer.discord.config;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class PropertiesConfigManager implements ConfigManager {

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
        File path = new File("plugins/TRSSMPPlugin");
        path.mkdirs();
        File file = new File(path, "config.properties");
        if (!file.exists()) {
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("config.properties");
            try {
                Files.copy(is, Path.of("plugins/TRSSMPPlugin/config.properties"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            Files.readAllLines(file.toPath()).forEach(line -> {
                kvMap.put(line.split("=")[0], line.split("=")[1]);
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean save() {
        try {
            FileWriter writer = new FileWriter("plugins/TRSSMPPlugin/config.properties");
            for (Map.Entry<String, String> entry : kvMap.entrySet()) {
                writer.write(entry.getKey() + "=" + entry.getValue() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}