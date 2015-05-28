package com.sherman.itest;

import com.google.inject.Inject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sherman.entity.Word;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MyResourceIT {

    @Inject
    private EntityManager em;

    @Before
    public void before() {
//        em = Persistence.createEntityManagerFactory("h2DB").createEntityManager();

    }

    @After
    public void after() {
        if (em != null && em.getTransaction().isActive()) {
            em.getTransaction().commit();

        }
    }

    @Test
    public void test() {

        assertNotNull("em cannot be null ", em);
        em.getTransaction().begin();

        Word w = new Word();
        w.setText("elo");

        em.persist(w);
        em.flush();


        em.find(Word.class, w.getId());
        assertNotNull(w);
        assertEquals(w.getText(), "elo");

    }
}
