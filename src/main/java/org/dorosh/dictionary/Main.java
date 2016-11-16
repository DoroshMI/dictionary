package org.dorosh.dictionary;

import java.util.ArrayList;

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

    // 1-Creates an instance of book
	    ArrayList<String> translatedWords=new ArrayList<>();
	    translatedWords.add("добре");
	    translatedWords.add("чудово");
	    
	  	Word word = new Word("good", "EN");
	  	word.setTranslatedWords(translatedWords);
	  	
	  	Word word1 = new Word("great", "EN");
	  	translatedWords=new ArrayList<>();
	  	translatedWords.add("великий");
	    translatedWords.add("добре");
	  	
	  	word1.setTranslatedWords(translatedWords);

	   
	   // 2-Obtains an entity manager and a transaction
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("dictionary");
	    EntityManager em = emf.createEntityManager();

	    // 3-Persists the book to the database
	    EntityTransaction tx = em.getTransaction();
	    tx.begin();
	    em.persist(word);
	    em.persist(word1);
	    tx.commit();

	    // 4-Executes the named query
	    word = em.createNamedQuery("findAllWords", Word.class).getSingleResult();

	    System.out.println("######### " + word.getOriginalWord());
	    System.out.println("######### " + word.getTranslatedWords());

	    // 5-Closes the entity manager and the factory
	    em.close();
	    emf.close();  

	  	
	  	
//  Book book = new Book("H2G2", "The Hitchhiker's Guide to the Galaxy", 12.5F, "1-84023-742-2", 354, false);
//
//    // 2-Obtains an entity manager and a transaction
//    EntityManagerFactory emf = Persistence.createEntityManagerFactory("chapter04PU");
//    EntityManager em = emf.createEntityManager();
//
//    // 3-Persists the book to the database
//    EntityTransaction tx = em.getTransaction();
//    tx.begin();
//    em.persist(book);
//    tx.commit();
//
//    // 4-Executes the named query
//    book = em.createNamedQuery("findBookH2G2", Book.class).getSingleResult();
//
//    System.out.println("######### " + book.getDescription());
//
//    // 5-Closes the entity manager and the factory
//    em.close();
//    emf.close();

  }
}