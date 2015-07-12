package com.word.learner.itest.testcase.entity;

import com.word.learner.entity.User;
import com.word.learner.itest.util.ITestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(ITestRunner.class)
public class UserIT extends BaseEntityTest {


    @Test
    public void testSaving() {
        User u1 = getUserAndPersist("user1", "password", "userName");

        em.flush();

    }


    private User getUserAndPersist(String login, String password, String name) {

        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setName(name);
        em.persist(user);
        return user;
    }

}
