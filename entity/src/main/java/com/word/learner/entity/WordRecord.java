package com.word.learner.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name="word_record",uniqueConstraints = @UniqueConstraint(columnNames = {"parent_word", "translation_word", "app_user"}))
public class WordRecord extends IdBase {


    @ManyToOne
    @JoinColumn(name = "parent_word", nullable = false)
    private Word parentWord;

    @ManyToOne
    @JoinColumn(name = "translation_word", nullable = false)
    private Word translationWord;

    @ManyToOne
    @JoinColumn(name = "app_user", nullable = false, unique = false)
    private User user;

    @Column
    private LocalDate lastDate;

    @Column
    private Integer answersCount;

    @Column
    private Integer correctAnswersCount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WordRecord that = (WordRecord) o;
        return Objects.equals(parentWord, that.parentWord) &&
                Objects.equals(translationWord, that.translationWord) &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parentWord, translationWord, user);
    }

    public Word getParentWord() {
        return parentWord;
    }

    public void setParentWord(Word parentWord) {
        this.parentWord = parentWord;
    }

    public Word getTranslationWord() {
        return translationWord;
    }

    public void setTranslationWord(Word translationWord) {
        this.translationWord = translationWord;
    }

    public LocalDate getLastDate() {
        return lastDate;
    }

    public void setLastDate(LocalDate lastDate) {
        this.lastDate = lastDate;
    }

    public Integer getAnswersCount() {
        return answersCount;
    }

    public void setAnswersCount(Integer answersCount) {
        this.answersCount = answersCount;
    }

    public Integer getCorrectAnswersCount() {
        return correctAnswersCount;
    }

    public void setCorrectAnswersCount(Integer correctAnswersCount) {
        this.correctAnswersCount = correctAnswersCount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

