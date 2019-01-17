package edu.eskola.muba.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity of User
 * 
 * @author MUBA team
 * @version Final version
 */

@Entity
@Table(name = "USER")
public class User {

   @Id
   @Column(name = "USERID", insertable = false, updatable = false)
   private int userId;

   @Column(name = "USERNAME")
   private String username;

   @Column(name = "PASSWORD")
   private String password;

   /**
    * Empty constructor of User
    */
   public User() {}
   
   /**
    * Constructor with the parameters to set to the user
    * 
    * @param username Username to set to the user
    * @param password Password to set to the user
    */
   public User(String username, String password) {
      this.username = username;
      this.password = password;
   }

   /**
    * 
    * @return Returns the userId
    */
	public int getUserId() {
		return userId;
	}
	
	/**
	 * 
	 * @return Returns the username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * 
	 * @return Return the password
	 */
	public String getPassword() {
		return password;
	}
}