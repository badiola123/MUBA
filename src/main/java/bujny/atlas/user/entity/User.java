package bujny.atlas.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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

   @Column(name = "ISADMIN")
   private  boolean isAdmin;

   public User() {}

   public User(String username, String password) {
      this.username = username;
      this.password = password;
      this.isAdmin = false;
   }

	public int getUserId() {
		return userId;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public boolean isAdmin() {
   	return isAdmin;
	}
}