/**
  *
  *
  */

package org.dorosh.dictionary;

import java.util.Date;
import java.util.ArrayList;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.MapKeyColumn;
import javax.persistence.GeneratedValue;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Marya Dorosh
 *         
 */
@Entity
@Table(name="Words")
@NamedQueries({
        @NamedQuery(name = "findAllWords", query = "SELECT w FROM Word w WHERE w.originalWord='good'")
       })
@IdClass(IdWord.class)
public class Word {

  // ======================================
  // =             Attributes             =
  // ======================================

  @Id
  @GeneratedValue
  private Long id;
  @Id 
  private String language;
  
  @Column(name="original_word", nullable=false)
  private String originalWord;
  /*
   * @param <c>date</c> describe when word was inserted in database
   */
  @NotNull
  @Temporal(TemporalType.DATE)
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
   * many to many
   */
  @ElementCollection(fetch=FetchType.EAGER)
  @CollectionTable(name="the_translation")
   // @Transient
  private ArrayList<String> translatedWords;
 
  /*
   * Collection of related Words
   */
  @ElementCollection(fetch=FetchType.EAGER)
  @CollectionTable(name="relation")
 
  private ArrayList<String> relatedWords;
  

  // ======================================
  // =            Constructors            =
  // ======================================

  public Word() {
  }

  public Word(String originalWord, @Size(min = 2, max = 2) String language) {
	  this.setOriginalWord(originalWord);
	  this.language=language;
  }

  // ======================================
  // =          Getters & Setters         =
  // ======================================
  public Long getId() {
	  return id;
  }

  public void setId(Long id) {
		this.id = id;
  }
  
  public String getLanguage() {
	  return language;
  }

  public void setLanguage(String language) {
	  this.language = language;
  }


  public String getOriginalWord() {
	  return originalWord;
  }

  public void setOriginalWord(String originalWord) {
	  this.originalWord = originalWord;
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

  public ArrayList<String> getTranslatedWords() {
		return translatedWords;
	}

  public void setTranslatedWords(ArrayList<String> translatedWords) {
		this.translatedWords = translatedWords;
	}

  public ArrayList<String> getRelatedWords() {
		return relatedWords;
	}

  public void setRelatedWords(ArrayList<String> relatedWords) {
		this.relatedWords = relatedWords;
	}
  

  // ======================================
  // =         hash, equals, toString     =
  // ======================================

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder();
    sb.append("Word");
    sb.append("{id=").append(getId());
    sb.append(getOriginalWord());
    sb.append(" translade as ");
    sb.append(getTranslatedWords());
    sb.append('}');
    return sb.toString();
  }

}