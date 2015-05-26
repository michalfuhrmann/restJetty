package com.sherman.itest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.junit.runners.JUnit4;
import sherman.entity.Word;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MyResourceIT {


    private EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("h2DB");
    private EntityManager em;

    @Before
    public void before() {
        em = emFactory.createEntityManager();
        em.getTransaction().begin();
    }

    @After
    public void after() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();

        }
    }

    @Test
    public void test() {


        Word w = new Word();
        w.setText("elo");

        em.persist(w);
        em.flush();


        em.find(Word.class, w.getId());
        assertNotNull(w);
        assertEquals(w.getText(), "elo");

    }
}
