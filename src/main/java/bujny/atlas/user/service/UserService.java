package bujny.atlas.user.service;

import java.util.List;
import bujny.atlas.user.entity.User;

public interface UserService {
    void add(User user);
    void remove(User user);
    User get(int userId);
    List<User> listUsers();
    User checkUser(String username, String password);
    int changePass(String newPass, int userId);
    int checkUsername(String username);
}