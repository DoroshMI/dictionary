/**
 *  
 */
package org.dorosh.dictionary.member.persistence;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Represents an Dictionary employee
 * @author Maryan Dorosh
 */
@Entity
@Table(name="students")
@DiscriminatorValue(value="S")
@PrimaryKeyJoinColumn(name="USER_ID")
public class Student extends User {
	 public Student() {}

	    /**
	     * Constructs a new employee
	     * @param firstName - first name
	     * @param lastName - last name
	     * @param username - username
	     * @param password - password
	     * @param dateCreated - date created
	     */
	    public Student(String firstName, String lastName, String username, String password, Date dateCreated) {
	        super(firstName,lastName,username,password,dateCreated,true);   
	       
	    }
}
