package com.word.learner.itest.testcase.entity;

import com.word.learner.entity.Language;
import com.word.learner.entity.Word;
import com.word.learner.itest.util.ITestRunner;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.persistence.PersistenceException;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(ITestRunner.class)
public class WordIT extends BaseEntityTest {


    @Test
    public void testSaving() {
        Word word = new Word();
        word.setLanguage(Language.ENGLISH);
        word.setText("parent");

        em.persist(word);

        em.flush();
        em.clear();

        Word savedWord = em.find(Word.class, word.getId());

        assertThat(word.getId(), Is.is(savedWord.getId()));
        assertThat(word.getLanguage(), Is.is(savedWord.getLanguage()));
        assertThat(word.getText(), Is.is(savedWord.getText()));

    }

    @Test(expected = PersistenceException.class)
    public void checkConstraintOverColumns() {


        Word word = new Word();
        word.setLanguage(Language.ENGLISH);
        word.setText("parent");

        em.persist(word);
        em.flush();

        Word secondWord = new Word();
        secondWord.setLanguage(Language.ENGLISH);
        secondWord.setText("parent");

        em.persist(secondWord);
        em.flush();

    }

    @Test
    public void checkIfAddingTranslationsToWordSavesItBidirectionally() {

        Word word = new Word();
        word.setLanguage(Language.ENGLISH);
        word.setText("parent");


        Word translation = new Word();
        translation.setLanguage(Language.POLISH);
        translation.setText("rodzic");


        word.addTranslation(translation);

        em.persist(word);
        em.persist(translation);

        em.flush();
        em.clear();

        word = em.find(Word.class, word.getId());
        translation = em.find(Word.class, translation.getId());


        assertThat(word.getTranslations().size(), is(1));
        assertThat(word.getTranslations(), hasItem(translation));

        assertThat(translation.getTranslations().size(), is(1));
        assertThat(translation.getTranslations(), hasItem(word));


    }


}
