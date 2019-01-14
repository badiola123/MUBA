package edu.eskola.muba.user.dao;

import java.util.List;
import edu.eskola.muba.user.entity.User;

public interface UserDao {
   void add(User user);
   List<User> listUsers();
   User checkUser(String username, String password);
   int changePass(String newPass, int userId);}
