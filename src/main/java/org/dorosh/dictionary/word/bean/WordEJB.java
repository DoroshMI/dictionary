package org.dorosh.dictionary.word.bean;

import java.util.List;

import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.dorosh.dictionary.word.persistence.Word;

@Stateless
@Named
public class WordEJB {
	  // ======================================
	  // =             Attributes             =
	  // ======================================
	  
	  // 1-Obtains an entity manager
	  @PersistenceContext
	  private EntityManager em;

	  // ======================================
	  // =          Business methods          =
	  // ======================================
	  public Word createWord(Word word) {
	    em.persist(word);
	    return word;
	  }

	  public Word updateWord(Word word) {
	    em.merge(word);
	    return word;
	  }

	  public List<Word> findAllWords() {
	    return em.createNamedQuery("findAllWords", Word.class).getResultList();
	  }

	  public Word findWordById(String originalWord) {
	    return em.find(Word.class, originalWord);
	  }
}
