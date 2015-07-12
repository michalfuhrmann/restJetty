package com.word.learner.entity;

import com.google.common.collect.Sets;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="word",uniqueConstraints = @UniqueConstraint(columnNames = {"text", "language"}))
public class Word extends IdBase {

    @Column
    private String text;

    @Column
    @Enumerated(EnumType.STRING)
    private Language language;

    @OneToMany
    private Set<Word> translations = Sets.newHashSet();

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Set<Word> getTranslations() {
        return translations;
    }

    public void addTranslation(Word translation) {
        if (translations == null) {
            translations = Sets.newHashSet();
        }
        translations.add(translation);
        if (!translation.getTranslations().contains(this)) {
            translation.addTranslation(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return Objects.equals(text, word.text) &&
                Objects.equals(language, word.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, language);
    }

    @Override
    public String toString() {
        return String.format("Word: %s, language: %s", text, language);
    }
}

