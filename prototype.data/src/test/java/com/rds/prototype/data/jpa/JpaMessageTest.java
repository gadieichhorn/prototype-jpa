/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rds.prototype.data.jpa;

import com.rds.prototype.data.jpa.Message;
import com.rds.prototype.data.AbstractJpaTestBase;
import java.util.Arrays;
import java.util.Collection;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author gadeichhorn
 */
@RunWith(value = Parameterized.class)
public class JpaMessageTest extends AbstractJpaTestBase {

    private static final Logger logger = LoggerFactory.getLogger(JpaMessageTest.class);
    private final String name;
    private Message instance;

    public JpaMessageTest(String name) {
        this.name = name;
        logger.info("NAME: {}", name);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{
            {"Task"},
            {"Engineer"},
            {"Assignment"},
            {"Calendar"}
        };
        return Arrays.asList(data);
    }

    @Before
    public void setUp() {
        instance = new Message(name);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class Message.
     */
    @Test
    public void testGetId() {
        
        tx.begin();
        em.persist(instance);
        tx.commit();

        assertTrue(instance.getId() > 0L);
        Message output = em.find(Message.class, instance.getId());
        assertTrue(output.getId()> 0L);
    }
    
    @Test
    public void testGetRevision() {

        tx.begin();
        em.persist(instance);
        tx.commit();

        Message output = em.find(Message.class, instance.getId());
        assertTrue(output.getRevision() > 0L);
    }
    
}
