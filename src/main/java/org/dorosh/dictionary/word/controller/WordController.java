package org.dorosh.dictionary.word.controller;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import java.util.Date;
import java.util.ArrayList;

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

  private String allTranslatedWords;

  // ======================================
  // =           Public Methods           =
  // ======================================

  public String doCreateWord() {

    //1-Перевіряємо чи є наявне слова в базі
    if ( wordEJB.findWordById(word.getOriginalWord())==null){
      word.setLanguage("EN");
      word.setDate(new Date());
      word.setStudy(false);
      word.setTranslatedWords(new ArrayList<Word>());

      word=wordEJB.createWord(word);
    }
    else{
      word= wordEJB.findWordById(word.getOriginalWord());
    }

    //2-Перевіряємо чи є наявне слова-переклад в базі
    if ( wordEJB.findWordById(translatedWord.getOriginalWord())==null){
      translatedWord.setLanguage("UA");
      translatedWord.setDate(new Date());
      translatedWord.setStudy(false);
      translatedWord.setTranslatedWords(new ArrayList<Word>());

      wordEJB.createWord(translatedWord);
    }
    else{
      translatedWord=wordEJB.findWordById(translatedWord.getOriginalWord());
    }

	if (!word.getTranslatedWords().contains(translatedWord)) {
		word.getTranslatedWords().add(translatedWord);
		translatedWord.getTranslatedWords().add(word);
	}

	wordEJB.updateWord(word);
	wordEJB.updateWord(translatedWord);
 
    return "newWord.xhtml";
  }

  public String doTranslateWord() {
    Word tempWord=word;
    word=wordEJB.findWordById(word.getOriginalWord());
    if (word==null) {
      word=tempWord;
      translatedWord=tempWord;
    }
    else{
      translatedWord=wordEJB.findWordById(word.getTranslatedWords().get(0).getOriginalWord());
      StringBuilder sb = new StringBuilder();
      for(Word w : word.getTranslatedWords()){
      	 
      	sb.append(w.getOriginalWord());
      	sb.append("\n");
      }
      allTranslatedWords=sb.toString();	
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

  public String getAllTranslatedWords() { 
    return allTranslatedWords;
  }

  public void setAllTranslatedWords(String allTranslatedWords) {
    this.allTranslatedWords = allTranslatedWords;
  }
}