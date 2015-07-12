package com.word.learner.service.impl;

import com.word.learner.entity.Language;
import com.word.learner.entity.Word;
import com.word.learner.service.api.IWordRecordService;
import com.word.learner.service.api.NoSuchWordException;

import java.util.List;

public class WordRecordService implements IWordRecordService {
    public void addWord(String text, Language language, String translation, Language translationLanguage) throws NoSuchWordException {

    }

    public List<Word> getTranslations(String text, Language language) {
        return null;
    }

    public void removeTranslation(String text, Language language, Word translation) {

    }
}
