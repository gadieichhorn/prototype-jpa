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
        Map<String, Message> payload = new HashMap<String, Message>();
        MessageCacheSerialiser mcs = new MessageCacheSerialiser();
        byte[] cache = mcs.serialiseMessages(payload);
        instance.setCache(cache);

        Map<String, Message> output = mcs.deserialiseMessages(instance.getCache());
        assertNotNull(output);
        assertTrue(output instanceof HashMap);
    }

    @Test
    public void testGetCacheWithMessages() throws IOException, ClassNotFoundException {
        logger.info("getName");
        MessageCache instance = new MessageCache();
        Map<String, Message> payload = new HashMap<String, Message>();
        payload.put("1", new Message("TEST"));
        MessageCacheSerialiser mcs = new MessageCacheSerialiser();
        byte[] cache = mcs.serialiseMessages(payload);
        instance.setCache(cache);

        Map<String, Message> output = mcs.deserialiseMessages(instance.getCache());
        assertNotNull(output);
        assertTrue(output instanceof HashMap);
        assertThat("Name", output.get("1").getName(), equalTo("TEST"));
    }
}
