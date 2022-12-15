package nl.dizmizzer.core.provider;

import lombok.Getter;
import net.hypixel.api.HypixelAPI;
import net.hypixel.api.apache.ApacheHttpClient;

import java.util.UUID;

public class HypixelAPIProvider {
    @Getter
    private final HypixelAPI hypixelAPI;

    public HypixelAPIProvider(UUID apiKey) {
        this.hypixelAPI = new HypixelAPI(new ApacheHttpClient(apiKey));
    }
}
