package edu.eskola.muba.user.service;

import java.util.List;

import edu.eskola.muba.user.dao.UserDao;
import edu.eskola.muba.user.entity.User;

/**
 * Service of User
 * 
 * @author MUBA team
 * @version Final version
 * @see UserDao
 */

public interface UserService {
    void add(User user);
    List<User> listUsers();
    User checkUser(String username, String password);
    int changePass(String newPass, int userId);
    int checkUsername(String username);
}