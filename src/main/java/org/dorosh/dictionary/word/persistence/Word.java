/**
  *
  *
  */

package org.dorosh.dictionary.word.persistence;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyColumn;
import javax.persistence.GeneratedValue;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Marya Dorosh
 *         
 */
@Entity
@Table(name="WORDS")
@NamedQueries({
        @NamedQuery(name = "findAllWords", query = "SELECT w FROM Word w")
       })
public class Word {

  // ======================================
  // =             Attributes             =
  // ======================================

  @Id
  @NotNull
  @Column(name="Original_word", nullable=false)
  private String originalWord;
  
  @NotNull
  @Column(name="Language_Word", nullable=true)
  private String language;

  /*
   * @param <c>date</c> describe when word was inserted in database
   */
  @NotNull
  @Temporal(TemporalType.DATE)
  @Column(name="Date_of_create")
  private Date date;
  



  /*
   * @param study describe wheithe word is studied
   */
  private Boolean study;

  @Column(name="date_of_study")
  @Temporal(TemporalType.DATE)
  private Date dateOfStudy;
  
  /*
   * Collection of translated words
   * one to many
   */
  @ManyToMany()
  @JoinTable(name = "WORDS_AND_translatedWords")
  private  List<Word> translatedWords;
  

  // ======================================
  // =            Constructors            =
  // ======================================

  public Word() {
  }

  public Word(String originalWord, @Size(min = 2, max = 2) String language, Date date) {
	  this.setOriginalWord(originalWord);
	  this.language=language;
	  this.date=date;
	  //translatedWords=null;
  }

  // ======================================
  // =          Getters & Setters         =
  // ======================================
  
  
  public String getOriginalWord() {
	  return originalWord;
  }

  public void setOriginalWord(String originalWord) {
	  this.originalWord = originalWord;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public Date getDate() {
	  return date;
  }

  public void setDate(Date date) {
	  this.date = date;
  }


  public Boolean getStudy() {
    return study;
  }

  public void setStudy(Boolean study) {
    this.study = study;
  }

  public Date getDateOfStudy() {
    return dateOfStudy;
  }

  public void setDateOfStudy(Date dateOfStudy) {
    this.dateOfStudy = dateOfStudy;
  }
  
  public List<Word> getTranslatedWords() {
    return translatedWords;
  }

  public void setTranslatedWords(List<Word> translatedWords) {
    this.translatedWords = translatedWords;
  }





  // ======================================
  // =         hash, equals, toString     =
  // ======================================

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder();
    sb.append("Word");
    sb.append(getOriginalWord());
    sb.append(" translade as ");
    //sb.append(getTranslatedWords());
    sb.append('}');
    return sb.toString();
  }



}