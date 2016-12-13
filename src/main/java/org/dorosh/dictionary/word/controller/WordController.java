package org.dorosh.dictionary.word.controller;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import java.util.Date;

import org.dorosh.dictionary.word.persistence.Word;
import org.dorosh.dictionary.word.bean.WordEJB;

/**
 * @author Antonio Goncalves
 *         APress Book - Beginning Java EE 7 with Glassfish 4
 *         http://www.apress.com/
 *         http://www.antoniogoncalves.org
 *         --
 */
@Named
@RequestScoped
public class WordController {

  // ======================================
  // =             Attributes             =
  // ======================================

  @Inject
  private WordEJB wordEJB;
  private Word word = new Word();

  private Word translatedWord = new Word();

  // ======================================
  // =           Public Methods           =
  // ======================================

  public String doCreateWord() {
    System.out.println("Created word: " + word);
    word.setDate(new Date());
    word.setStudy(false);
    wordEJB.createWord(word);
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Word created",
            "The word" + word.getOriginalWord()));
    return "newWord.xhtml";
  }

  public String doTranslateWord() {
    Word tempWord=word;
    word=wordEJB.findWordById(word.getOriginalWord());
    if (word==null) {
      word=tempWord
      translatedWord=tempWord;
    }
    else{
      translatedWord=wordEJB.findWordById(word.getTranslatedWords().get(0).getOriginalWord());
    }
    return "newWord.xhtml";
  }

  public void doFindWordById() {
    word = wordEJB.findWordById(word.getOriginalWord());
  }

  // ======================================
  // =          Getters & Setters         =
  // ======================================

  public Word getWord() { 
    return word;
  }

  public void setWord(Word word) {
    this.word = word;
  }

  public Word getTranslatedWord() { 
    return translatedWord;
  }

  public void setTranslatedWord(Word translatedWord) {
    this.translatedWord = translatedWord;
  }
}