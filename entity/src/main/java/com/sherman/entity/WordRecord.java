package com.sherman.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"parentWord", "translationWord"}))
public class WordRecord extends IdBase {


    @ManyToOne
    @JoinColumn(name = "parentWord")
    private Word parentWord;

    @ManyToOne
    @JoinColumn(name = "translationWord")
    private Word translationWord;

    @Column
    private LocalDate lastDate;

    @Column
    private Integer answersCount;

    @Column
    private Integer correctAnswersCount;

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
}

