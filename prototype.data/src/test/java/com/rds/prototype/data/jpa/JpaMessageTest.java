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
    private String name;
    private String ref;
    private Message instance;

    public JpaMessageTest(String name, String ref) {
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
        instance = new Message();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class Message.
     */
    @Test
    public void testGetId() {
        instance.setName(name);
        tx.begin();
        em.persist(instance);
        tx.commit();
        assertTrue(instance.getId() > 0L);
    }

    @Test
    public void testGetRevision() {
        instance.setName(name);
        tx.begin();
        em.persist(instance);
        tx.commit();
        assertTrue(instance.getRevision() > 0L);
    }
    
}
