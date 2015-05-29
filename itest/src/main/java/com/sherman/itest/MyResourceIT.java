package com.sherman.itest;

import com.google.inject.Inject;
import com.sherman.entity.Word;
import com.sherman.itest.util.ITestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(ITestRunner.class)
public class MyResourceIT {

    @Inject
    private EntityManager em;

    @Before
    public void before() {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
    }

    @After
    public void after() {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }

    @Test
    public void test() {

        assertNotNull("em cannot be null ", em);
        Word w = new Word();
        w.setText("elo");

        em.persist(w);
        em.flush();


        em.find(Word.class, w.getId());
        assertNotNull(w);
        assertEquals(w.getText(), "elo");

    }
}
