/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rds.prototype.data;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.*;
import org.junit.rules.ExternalResource;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author gadeichhorn
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    com.rds.prototype.data.jpa.MessageTest.class,
    com.rds.prototype.data.jpa.JpaMessageTest.class
})
public class JpaModelTestSuite {

    private static EntityManagerFactory emf;
    @ClassRule
    public static ExternalResource resource = new ExternalResource() {

        @Override
        protected void after() {
            emf.close();
        }

        @Override
        protected void before() throws Throwable {
            emf = Persistence.createEntityManagerFactory("prototype.test");
        }
    };

    public static EntityManager createEntityManager() {
        return emf.createEntityManager();
    }

}
