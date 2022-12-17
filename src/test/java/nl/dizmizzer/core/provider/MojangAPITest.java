package nl.dizmizzer.core.provider;

import junit.framework.TestCase;
import org.junit.Assert;

import java.util.UUID;

public class MojangAPITest extends TestCase {

    public void testGetProfile() {
        Assert.assertEquals(UUID.fromString("a375f7d4-0f8f-4806-b230-1c7442c00e26"), MojangAPI.getUUID("DizMizzer"));
    }

    public void testNullOnGetUUID() {
        Assert.assertNull(MojangAPI.getUsername("UsernameThatDoesNotExist"));
    }

    public void testGetUsername() {
        Assert.assertEquals("DizMizzer", MojangAPI.getUsername(MojangAPI.addHyphensToUUID("a375f7d40f8f4806b2301c7442c00e26")));
    }

    public void testNullOnGetUsername() {
        Assert.assertNull( MojangAPI.getUsername(MojangAPI.addHyphensToUUID("a375f7d40f8f4806b2301c7442c00e28")));
    }
}
