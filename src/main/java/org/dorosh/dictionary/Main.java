package org.dorosh.dictionary;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * @author Antonio Goncalves
 *         APress Book - Beginning Java EE 7 with Glassfish 4
 *         http://www.apress.com/
 *         http://www.antoniogoncalves.org
 *         --
 */
public class Main {

  public static void main(String[] args) {

    // 1-Creates an instance of words
	  
	    
	  	Word wordEN1 = new Word( "good", "EN");
	  	Word wordEN2 = new Word( "sorry", "EN");
	  	Word wordUA1 = new Word( "добре", "UA");
	  	Word wordUA2 = new Word( "чудово", "UA");
	  	Word wordUA3 = new Word( "вибачати", "UA");
	  	
	  	 //2-setTranslatedWords(translatedWords);
	  	wordEN1.setTranslatedWords(new ArrayList<>());
	  	wordEN1.getTranslatedWords().add(wordUA1);
	  	wordEN1.getTranslatedWords().add(wordUA2);
	  	
	  	
	  	
	  	wordUA1.setTranslatedWords(new ArrayList<>()); 
	  	wordUA1.getTranslatedWords().add(wordEN1);
	
	   
	   // 3-Obtains an entity manager and a transaction
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("dictionary");
	    EntityManager em = emf.createEntityManager();

	    // 4-Persists the words to the database
	    EntityTransaction tx = em.getTransaction();
	    tx.begin();
	    em.persist(wordUA1);
	    em.persist(wordUA2);
	    em.persist(wordEN1);
	    tx.commit();
	      
	    // 5-Executes the named query
	     Word word = em.createNamedQuery("findAllWords", Word.class).getSingleResult();

	    System.out.println("#########: " + word.getOriginalWord());
	   
	    for (Word w : word.getTranslatedWords())
	    System.out.println("#########Translate: " + w.getOriginalWord());

	 
	    // 6-Closes the entity manager and the factory
	    em.close();
	    emf.close();  

	  	
	  	


  }
}