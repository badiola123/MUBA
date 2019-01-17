package edu.eskola.muba.user.dao;

import java.util.List;

import edu.eskola.muba.user.entity.User;

/**
 * DAO of User
 *
 * @author MUBA team
 * @version Final version
 */
public interface UserDao {
	   /**
	    * Adds a new user to the database
	    * 
	    * @param user User to be added
	    */
	   void add(User user);
	   
	   /**
	    * Gets and returns a list containing all the users from the database
	    * 
	    * @return A list containing all the registered users
	    */
	   List<User> listUsers();
	   
	   /**
	    * Tries to get a user from the database with the specified username and password
	    * 
	    * @param username Username to look for in the database
	    * @param password Password to look for in the database
	    * @return Null if the specified user is not registered and a User object with the user fetched if it exists
	    */
	   User checkUser(String username, String password);
	   
	   /**
	    * Changes the password of the specified userId
	    * 
	    * @param newPass New password that is inserted into the database
	    * @param userId Parameter to identify the user in the database
	    * @return 1 if the update was successful
	    */
	   int changePass(String newPass, int userId);
	   
	   /**
	    * Checks if a username exists in the database
	    * 
	    * @param username Username to check in the database
	    * @return -1 if the username does not exist and its id if it exists
	    */
	   int checkUsername(String username);
	}