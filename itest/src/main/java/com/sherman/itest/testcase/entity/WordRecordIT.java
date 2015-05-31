package com.sherman.itest.testcase.entity;

import com.sherman.entity.Language;
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
        Word w1 = getWord("polish", Language.POLISH);
        Word w2 = getWord("english", Language.ENGLISH);

        WordRecord wordRecord = getWordRecord(w1, w2);

        em.flush();
        em.clear();

        wordRecord = em.find(WordRecord.class, wordRecord.getId());
        assertThat(wordRecord.getParentWord(), is(w1));
        assertThat(wordRecord.getTranslationWord(), is(w2));

    }


    @Test
    public void testWordRecordCanBeCreatedBidirectionally() {
        Word w1 = getWord("polish", Language.POLISH);
        Word w2 = getWord("english", Language.ENGLISH);

        WordRecord wordRecord = getWordRecord(w1, w2);
        WordRecord wordRecord2 = getWordRecord(w2, w1);

        em.flush();
        em.clear();

        assertThat(wordRecord.getParentWord(), is(w1));
        assertThat(wordRecord.getTranslationWord(), is(w2));

        assertThat(wordRecord2.getParentWord(), is(w2));
        assertThat(wordRecord2.getTranslationWord(), is(w1));

    }

    @Test(expected = PersistenceException.class)
    public void testWordRecordDoesNotAllowDuplicates() {
        Word w1 = getWord("polish", Language.POLISH);
        Word w2 = getWord("english", Language.ENGLISH);

        WordRecord wordRecord = getWordRecord(w1, w2);
        WordRecord wordRecord2 = getWordRecord(w1, w2);

        em.flush();
        em.clear();
    }


    @Test
    public void testWordRecordForOneWordCanBeDuplicated() {
        Word w1 = getWord("polish", Language.POLISH);
        Word w2 = getWord("english", Language.ENGLISH);
        Word w3 = getWord("german", Language.GERMAN);


        WordRecord wordRecord = getWordRecord(w1, w2);
        WordRecord wordRecord2 = getWordRecord(w1, w3);

        em.flush();
        em.clear();
    }


    private Word getWord(String text, Language language) {
        Word w1 = new Word();
        w1.setText(text);
        w1.setLanguage(language);

        em.persist(w1);
        return w1;
    }

    private WordRecord getWordRecord(Word w1, Word w2) {
        WordRecord wordRecord = new WordRecord();
        wordRecord.setParentWord(w1);
        wordRecord.setTranslationWord(w2);
        em.persist(wordRecord);
        return wordRecord;
    }


}
