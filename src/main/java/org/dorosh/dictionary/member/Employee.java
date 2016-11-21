/**
 *  
 */
package org.dorosh.dictionary.member;

import java.io.Serializable;
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
@Table(name="employees")
@DiscriminatorValue(value="E")
@PrimaryKeyJoinColumn(name="USER_ID")
public class Employee extends User implements Serializable {

    /**
     * Employee title
     */
    private String title;
    
    /**
     * Default constructor for JPA
     */
    public Employee() {}

    /**
     * Constructs a new employee
     * @param firstName - first name
     * @param lastName - last name
     * @param username - username
     * @param password - password
     * @param dateCreated - date created
     * @param title - employee title
     */
    public Employee(String firstName, String lastName, String username, String password, Date dateCreated, String title) {
        super(firstName,lastName,username,password,dateCreated,true);   
        this.title = title;
    }

    /**
     * Returns the employee's title
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the employee's title
     * @param title - title
     */
    public void setTitle(String title) {
        this.title = title;
    }
}
