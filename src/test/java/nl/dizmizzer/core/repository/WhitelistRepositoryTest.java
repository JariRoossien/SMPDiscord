package nl.dizmizzer.core.repository;

import nl.dizmizzer.core.entity.WhitelistPlayer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

public class WhitelistRepositoryTest {

    WhitelistRepository whitelistRepository = new BasicWhitelistRepository();

    @Before
    public void addPlayers() {
        whitelistRepository.addPlayer(new WhitelistPlayer(UUID.fromString(addHyphensToUUID("a375f7d40f8f4806b2301c7442c00e26")), true));
    }

    @Test
    public void isWhitelisted() {
        Assert.assertTrue(whitelistRepository.isWhitelisted(UUID.fromString(addHyphensToUUID("a375f7d40f8f4806b2301c7442c00e26"))));
        Assert.assertFalse(whitelistRepository.isWhitelisted(UUID.fromString(addHyphensToUUID("a375f7d40f8f4806b2301c7442c00e27"))));
    }

    @Test
    public void addPlayer() {
        UUID uuid = UUID.randomUUID();
        Assert.assertFalse(whitelistRepository.isWhitelisted(uuid));
        Assert.assertTrue(whitelistRepository.addPlayer(new WhitelistPlayer(uuid, false)));
        Assert.assertTrue(whitelistRepository.isWhitelisted(uuid));
        Assert.assertFalse(whitelistRepository.addPlayer(new WhitelistPlayer(uuid, false)));
    }

    @Test
    public void getPlayerFrom() {
        WhitelistPlayer test = new WhitelistPlayer(UUID.randomUUID(), true);
        UUID testUuid = test.getPlayerId();
        Assert.assertNotNull(testUuid);
        Assert.assertTrue(whitelistRepository.addPlayer(test));
        Assert.assertEquals(test, whitelistRepository.getPlayerFrom(testUuid));
    }

    public static String addHyphensToUUID(String uuid) {
        return uuid.substring(0, 8) + "-" + uuid.substring(8, 12) + "-" + uuid.substring(12, 16) + "-" + uuid.substring(16, 20) + "-" + uuid.substring(20);
    }
}
