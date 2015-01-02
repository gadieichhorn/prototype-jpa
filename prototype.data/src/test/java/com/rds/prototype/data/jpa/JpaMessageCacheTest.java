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
    private final Map<String, Message> messages = new HashMap<String, Message>();
    
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
    public void setUp() throws IOException {
        message = new Message(name);
        messages.put(ref, message);
        instance = new MessageCache();

        Cache cache = new Cache();
        cache.serialise(messages);
        instance.setCache(cache);
    
    }
    
    @After
    public void tearDown() {
    }

    /**
     * @throws java.io.IOException
     */
    @Test
    public void testGetId() throws IOException {        

        tx.begin();
        em.persist(instance);
        tx.commit();

        // load persisted objects from DB
        MessageCache mc1 = em.find(MessageCache.class, instance.getId());
        assertTrue(mc1.getId()> 0L);        
    }
    
    @Test
    public void testGetRevision() throws IOException {
        
        tx.begin();
        em.persist(instance);
        tx.commit();

        // loading persisted results
        MessageCache mc1 = em.find(MessageCache.class, instance.getId());
        assertTrue(mc1.getRevision() > 0L);
    }
    
    @Test
    public void testGetCache() throws IOException, ClassNotFoundException {

        tx.begin();
        em.persist(message);
        em.persist(instance);
        tx.commit();

        // get the saved object from database
        MessageCache results = em.find(MessageCache.class, instance.getId());

        assertNotNull(results.getCache());
        logger.info(results.getCache().toString());

        Map<String, Message> output = (Map<String, Message>) results.getCache().deserialise();
        logger.info(output.toString());
        assertFalse(output.isEmpty());

        assertThat("Has the message key/name", output.get(ref).getName(), equalTo(name));
    }
    
}
