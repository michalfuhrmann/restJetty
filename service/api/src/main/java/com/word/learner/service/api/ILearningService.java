package com.word.learner.service.api;

import com.word.learner.entity.Language;
import com.word.learner.entity.User;
import com.word.learner.entity.WordRecord;

public interface ILearningService {

    WordRecord fetchNextWord(Language language);

    void validateUserAnswer(WordRecord record,String userAnswer);
}
