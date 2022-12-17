package nl.dizmizzer.core.config;

public interface ConfigManager {

    String getStringFrom(String key);
    boolean hasKey(String key);

    void load();

    boolean save();
}
