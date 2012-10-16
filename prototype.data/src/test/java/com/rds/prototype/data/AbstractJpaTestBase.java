/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rds.prototype.data;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.junit.After;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author gadeichhorn
 */
public class AbstractJpaTestBase {

    private static final Logger logger = LoggerFactory.getLogger(AbstractJpaTestBase.class);
    protected EntityManager em;
    protected EntityTransaction tx;

    @Before
    public void setEntityManager() throws Exception {
        em = JpaModelTestSuite.createEntityManager();
        assertNotNull(em);
        tx = em.getTransaction();
        assertNotNull(tx);
    }

    @After
    public void closeEntityManager() throws Exception {
        em.close();
    }
    
    public static String generateRandomString() {
        return Long.toHexString(Double.doubleToLongBits(Math.random()));
    }
    
}
