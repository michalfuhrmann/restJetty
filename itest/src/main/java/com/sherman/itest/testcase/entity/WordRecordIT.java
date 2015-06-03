package com.sherman.itest.testcase.entity;

import com.sherman.entity.Language;
import com.sherman.entity.User;
import com.sherman.entity.Word;
import com.sherman.entity.WordRecord;
import com.sherman.itest.util.ITestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.persistence.PersistenceException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(ITestRunner.class)
public class WordRecordIT extends BaseEntityTest {


    @Test
    public void testSaving() {
        Word w1 = getWordAndPersist("polish", Language.POLISH);
        Word w2 = getWordAndPersist("english", Language.ENGLISH);
        User u1 = getUserAndPersist("user1", "password", "userName");

        WordRecord wordRecord = getWordRecordAndPersist(w1, w2, u1);

        em.flush();
        em.clear();

        wordRecord = em.find(WordRecord.class, wordRecord.getId());
        assertThat(wordRecord.getParentWord(), is(w1));
        assertThat(wordRecord.getTranslationWord(), is(w2));

    }


    @Test
    public void testWordRecordCanBeCreatedBidirectionally() {
        Word w1 = getWordAndPersist("polish", Language.POLISH);
        Word w2 = getWordAndPersist("english", Language.ENGLISH);
        User u1 = getUserAndPersist("user1", "password", "userName");

        WordRecord wordRecord = getWordRecordAndPersist(w1, w2, u1);
        WordRecord wordRecord2 = getWordRecordAndPersist(w2, w1, u1);

        em.flush();
        em.clear();

        assertThat(wordRecord.getParentWord(), is(w1));
        assertThat(wordRecord.getTranslationWord(), is(w2));

        assertThat(wordRecord2.getParentWord(), is(w2));
        assertThat(wordRecord2.getTranslationWord(), is(w1));

    }

    @Test(expected = PersistenceException.class)
    public void testWordRecordDoesNotAllowDuplicates() {
        Word w1 = getWordAndPersist("polish", Language.POLISH);
        Word w2 = getWordAndPersist("english", Language.ENGLISH);
        User u1 = getUserAndPersist("user1", "password", "userName");

        WordRecord wordRecord = getWordRecordAndPersist(w1, w2, u1);
        WordRecord wordRecord2 = getWordRecordAndPersist(w1, w2, u1);

        em.flush();
        em.clear();
    }


    @Test
    public void shouldAllowInsertWordRecordWithSameWordsButDifferentUsers() {
        Word w1 = getWordAndPersist("polish", Language.POLISH);
        Word w2 = getWordAndPersist("english", Language.ENGLISH);
        User u1 = getUserAndPersist("user1", "password", "userName");
        User u2 = getUserAndPersist("user2", "password2", "userName");

        WordRecord wordRecord = getWordRecordAndPersist(w1, w2, u1);
        WordRecord wordRecord2 = getWordRecordAndPersist(w1, w2, u2);

        em.flush();
        em.clear();
    }


    private Word getWordAndPersist(String text, Language language) {
        Word w1 = new Word();
        w1.setText(text);
        w1.setLanguage(language);
        em.persist(w1);
        return w1;
    }

    private WordRecord getWordRecordAndPersist(Word w1, Word w2, User user) {
        WordRecord wordRecord = new WordRecord();
        wordRecord.setParentWord(w1);
        wordRecord.setTranslationWord(w2);
        wordRecord.setUser(user);
        em.persist(wordRecord);
        return wordRecord;
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
