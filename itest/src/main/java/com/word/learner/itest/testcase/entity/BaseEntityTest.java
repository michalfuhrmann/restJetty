package com.word.learner.itest.testcase.entity;

import com.google.inject.Inject;
import com.word.learner.itest.testcase.BaseTest;
import org.junit.After;
import org.junit.Before;

import javax.persistence.EntityManager;

public abstract class BaseEntityTest extends BaseTest {

    @Inject
    protected EntityManager em;

    @Before
    public void before() {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
    }

    @After
    public void after() {
        em.getTransaction().rollback();
    }
}
