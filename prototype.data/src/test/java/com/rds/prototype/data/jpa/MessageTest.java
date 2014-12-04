/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rds.prototype.data.jpa;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author gadeichhorn
 */
public class MessageTest {
    
    private static final Logger logger = LoggerFactory.getLogger(MessageTest.class); 
    
    public MessageTest() {
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
        logger.info("getId");
        Message instance = new Message();
        instance.setId(10L);
        long result = instance.getId();
        assertEquals(10L, result);
    }

    /**
     * Test of getRevision method, of class Message.
     */
    @Test
    public void testGetRevision() {
        System.out.println("getRevision");
        Message instance = new Message();
        instance.setRevision(20L);
        long result = instance.getRevision();
        assertEquals(20L, result);
    }

    /**
     * Test of getName method, of class Message.
     */
    @Test
    public void testGetName() {
        logger.info("getName");
        Message instance = new Message();
        instance.setName("NAME");
        String result = instance.getName();
        assertEquals("NAME", result);
    }

}
