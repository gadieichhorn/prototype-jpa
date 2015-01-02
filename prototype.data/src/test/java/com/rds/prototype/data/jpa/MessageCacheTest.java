package com.rds.prototype.data.jpa;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.equalTo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author gadeichhorn
 */
public class MessageCacheTest {

    private static final Logger logger = LoggerFactory.getLogger(MessageCacheTest.class);

    public MessageCacheTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class Message.
     */
    @Test
    public void testGetId() {
        MessageCache instance = new MessageCache();
        instance.setId(10L);
        long result = instance.getId();
        assertEquals(10L, result);
    }

    /**
     * Test of getRevision method, of class Message.
     */
    @Test
    public void testGetRevision() {
        MessageCache instance = new MessageCache();
        instance.setRevision(20L);
        long result = instance.getRevision();
        assertEquals(20L, result);
    }

    /**
     * Test of getName method, of class Message.
     *
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    @Test
    public void testGetCache() throws IOException, ClassNotFoundException {
        MessageCache instance = new MessageCache();
        Map<String, Message> messages = new HashMap<String, Message>();
        Cache cache = new Cache();
        cache.serialise(messages);
        instance.setCache(cache);

        Map<String, Message> output = (Map<String, Message>) cache.deserialise();
        assertNotNull(output);
        assertTrue(output instanceof HashMap);
    }

    @Test
    public void testGetCacheWithMessages() throws IOException, ClassNotFoundException {
        logger.info("getName");
        MessageCache instance = new MessageCache();
        Map<String, Message> messages = new HashMap<String, Message>();
        messages.put("1", new Message("TEST1"));
        messages.put("2", new Message("TEST2"));

        Cache cache = new Cache();
        cache.serialise(messages);
        instance.setCache(cache);

        Map<String, Message> output = (Map<String, Message>) cache.deserialise();
        assertNotNull(output);
        assertTrue(output instanceof HashMap);
        assertThat("Name", output.get("1").getName(), equalTo("TEST1"));
        assertThat("Name", output.get("2").getName(), equalTo("TEST2"));
    }
}
