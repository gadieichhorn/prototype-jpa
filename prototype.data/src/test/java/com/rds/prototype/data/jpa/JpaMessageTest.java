/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rds.prototype.data.jpa;

import com.rds.prototype.data.AbstractJpaTestBase;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
        assertTrue(output.getId() > 0L);
    }

    @Test
    public void testGetRevision() {

        tx.begin();
        em.persist(instance);
        tx.commit();

        Message output = em.find(Message.class, instance.getId());
        assertTrue(output.getRevision() > 0L);
    }

    @Test
    public void testGetStamp() {

        tx.begin();
        em.persist(instance);
        tx.commit();

        Message output = em.find(Message.class, instance.getId());
        assertNotNull(output.getStamp());
    }

    @Test
    public void testGetStampMilliseconds() {

        tx.begin();
        em.persist(instance);
        tx.commit();

        Message output = em.find(Message.class, instance.getId());
        assertTrue(output.getStamp().getMilliseconds() > 0);
    }

    @Test
    public void testGetStampYear() {

        tx.begin();
        em.persist(instance);
        tx.commit();

        Message output = em.find(Message.class, instance.getId());
        assertTrue(output.getStamp().getYear() > 0);
    }

    @Test
    public void testGetStampYearWithFilter() {

        instance.getStamp().setYear(2010);
        tx.begin();
        em.persist(instance);
        tx.commit();
        
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Message> cq = cb.createQuery(Message.class);
        Root<Message> root = cq.from(Message.class);

        Predicate p = cb.conjunction();
        ParameterExpression<Integer> year = cb.parameter(Integer.class, "year");
        p = cb.and(p, cb.equal(root.get(Message_.stamp).get(Stamp_.year), year));

        cq.where(p);
        TypedQuery<Message> tq = em.createQuery(cq).setParameter("year", 2010);
        List<Message> messages = tq.getResultList();
        
        assertFalse(messages.isEmpty());
    }
}
