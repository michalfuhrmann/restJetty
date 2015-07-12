package com.word.learner.itest.testcase.service;

import com.word.learner.entity.Language;
import com.word.learner.entity.User;
import com.word.learner.entity.Word;
import com.word.learner.entity.WordRecord;
import com.word.learner.itest.util.ITestRunner;
import com.word.learner.service.api.IWordRecordService;
import com.word.learner.service.api.NoSuchWordException;
import org.hamcrest.core.Is;
import org.junit.*;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(ITestRunner.class)
public class WordServiceTest {

    @Inject
    private IWordRecordService wordService;

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


    @Test
    public void checkWordIsPersistedCorrectly() throws NoSuchWordException {
        Word word = createWordAndPersist("polish", Language.POLISH);
        Word word2 = createWordAndPersist("english", Language.ENGLISH);

        wordService.addWord(word.getText(), word.getLanguage(), word2.getText(), word2.getLanguage());
        Assert.fail();
        //tODO create Query to Db
    }

    @Test(expected = NoSuchWordException.class)
    public void checkIfExceptionIsThrownIfWordDoesNotExists() throws NoSuchWordException {
        Word word2 = createWordAndPersist("english", Language.ENGLISH);

        wordService.addWord("not existing word", Language.POLISH, word2.getText(), word2.getLanguage());
    }

    @Test
    public void checkIfTranslationsAreReturnedCorrectly() {
        Word word = createWordAndPersist("polish", Language.POLISH);
        Word word2 = createWordAndPersist("english", Language.ENGLISH);
        Word word3 = createWordAndPersist("german", Language.GERMAN);
        word.addTranslation(word2);
        word.addTranslation(word3);

        List<Word> translations = wordService.getTranslations(word.getText(), word.getLanguage());
        assertNotNull(translations);
        assertThat(translations.size(), is(2));
        assertTrue(translations.contains(word2));
        assertTrue(translations.contains(word3));
    }

    @Test
    public void checkEmptyListIsReturnedIfNoTranslationsAreFound() throws NoSuchWordException {
        Word word = createWordAndPersist("polish", Language.POLISH);
        List<Word> translations = wordService.getTranslations(word.getText(), word.getLanguage());

        assertNotNull(translations);
        assertThat(translations.size(), is(0));
    }


    @Test
    public void checkIfTranslationIsRemoved() throws NoSuchWordException {
        Word word = createWordAndPersist("polish", Language.POLISH);
        Word word2 = createWordAndPersist("english", Language.ENGLISH);
        User user1 = createUserAndPersist("login", "password", "name");
        WordRecord wordRecord = createWordRecordAndPersist(word, word2, user1);


        wordService.removeTranslation(word.getText(), word.getLanguage(), word2);
        WordRecord wordRecordFromDb = em.find(WordRecord.class, wordRecord.getId());
        assertNull(wordRecordFromDb);
    }

    private Word createWordAndPersist(String text, Language language) {
        Word w1 = new Word();
        w1.setText(text);
        w1.setLanguage(language);
        em.persist(w1);
        return w1;
    }

    private WordRecord createWordRecordAndPersist(Word w1, Word w2, User user) {
        WordRecord wordRecord = new WordRecord();
        wordRecord.setParentWord(w1);
        wordRecord.setTranslationWord(w2);
        wordRecord.setUser(user);
        em.persist(wordRecord);
        return wordRecord;
    }


    private User createUserAndPersist(String login, String password, String name) {

        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setName(name);
        em.persist(user);
        return user;
    }
}
