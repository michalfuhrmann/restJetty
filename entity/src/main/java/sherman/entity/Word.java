package sherman.entity;

import com.google.common.collect.Lists;

import javax.persistence.*;
import java.util.List;

@Entity
public class Word extends IdBase {

    @Column
    private String text;

    @Column
    @Enumerated(EnumType.STRING)
    private Language language;

    @OneToMany
    private List<Word> translations;


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

    public List<Word> getTranslations() {
        return translations;
    }

    public void addTranslation(Word translation) {
        if (translations == null) {
            translations = Lists.newArrayList();
        }
        translations.add(translation);
    }
}

