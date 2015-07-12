package com.word.learner.service.api;

import com.word.learner.entity.Language;
import com.word.learner.entity.Word;

import java.util.List;

public interface IWordRecordService {

    void addWord(String text, Language language, String translation, Language translationLanguage) throws NoSuchWordException;

    List<Word> getTranslations(String text, Language language);

    void removeTranslation(String text, Language language, Word translation);

    //TODO update translation

}
