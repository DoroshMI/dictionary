/**
 * Id for Word
 */
package org.dorosh.dictionary;

import javax.validation.constraints.Size;

/**
 * @author Marya Dorosh
 *         
 */
//
public class IdWord {
	
	// ======================================
	// =             Attributes             =
	// ======================================
	private Long id;
	
	/*
	 * puts the language
	 */
	@Size(min = 2, max = 2)
	private String language;

	// ======================================
	// =            Constructors            =
	// ======================================
	public IdWord() {
		 
	}

	public IdWord(Long id, String language) {
		 this.id=id;
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
	
	// ======================================
	// =         hash, equals, toString     =
	// ======================================
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;

	    IdWord newId = (IdWord) o;

	    if (!language.equals(newId.language)) return false;
	    if (!id.equals(newId.id)) return false;

	    return true;
	 }

	 @Override
	 public int hashCode() {
	    int result = id.hashCode();
	    result = 31 * result + language.hashCode();
	    return result;
	 }

}
