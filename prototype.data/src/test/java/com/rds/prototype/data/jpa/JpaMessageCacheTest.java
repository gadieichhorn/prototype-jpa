package com.rds.prototype.data.jpa;

import com.rds.prototype.data.AbstractJpaTestBase;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.equalTo;

/**
 *
 * @author gadeichhorn
 */
@RunWith(value = Parameterized.class)
public class JpaMessageCacheTest extends AbstractJpaTestBase {
    
    private static final Logger logger = LoggerFactory.getLogger(JpaMessageCacheTest.class);
    private final String name;
    private final String ref;
    private Message message;
    private MessageCache instance;
    private final Map<String, Message> cache = new HashMap<String, Message>();
    private MessageCacheSerialiser mcs;
    
    public JpaMessageCacheTest(String name, String ref) {
        this.name = name;
        this.ref = ref;
        logger.info("NAME: {} REF: {}", name, ref);
    }
    
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{
            {"Task", "1"},
            {"Engineer", "2"},
            {"Assignment", "3"},
            {"Calendar", "4"}
        };
        return Arrays.asList(data);
    }
    
    @Before
    public void setUp() {
        message = new Message(name);
        cache.put(ref, message);
        instance = new MessageCache();
        mcs = new MessageCacheSerialiser();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * @throws java.io.IOException
     */
    @Test
    public void testGetId() throws IOException {        
        instance.setCache(mcs.serialiseMessages(cache));
        
        tx.begin();
        em.persist(message);
        em.persist(instance);
        tx.commit();
        assertTrue(message.getId() > 0L);
        assertTrue(instance.getId() > 0L);
        
    }
    
    @Test
    public void testGetRevision() throws IOException {
        instance.setCache(mcs.serialiseMessages(cache));
        
        tx.begin();
        em.persist(message);
        em.persist(instance);
        tx.commit();
        
        assertTrue(message.getRevision() > 0L);
        assertTrue(instance.getRevision() > 0L);
    }
    
    @Test
    public void testGetCache() throws IOException, ClassNotFoundException {
        instance.setCache(mcs.serialiseMessages(cache));
        
        logger.info(cache.toString());
        logger.info(Arrays.toString(instance.getCache()));

        tx.begin();
        em.persist(message);
        em.persist(instance);
        tx.commit();
        
        assertNotNull(instance.getCache());
        logger.info(Arrays.toString(instance.getCache()));

        Map<String, Message> output = mcs.deserialiseMessages(instance.getCache());
        logger.info(output.toString());
        assertFalse(output.isEmpty());

        assertThat("Has the message key/name", output.get(ref).getName(), equalTo(name));
    }
    
}
