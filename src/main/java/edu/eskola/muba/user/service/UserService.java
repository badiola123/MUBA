package edu.eskola.muba.user.service;

import java.util.List;
import edu.eskola.muba.user.entity.User;

public interface UserService {
    void add(User user);
    List<User> listUsers();
    boolean checkUser(String username, String password);
}
