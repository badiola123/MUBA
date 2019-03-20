package bujny.atlas.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bujny.atlas.user.dao.UserDao;
import bujny.atlas.user.entity.User;


@Service
public class UserServiceImpl implements UserService {

   @Autowired
   private UserDao userDao;

   @Transactional
   @Override
   public void add(User user) {
      userDao.add(user);
   }

   @Transactional
   @Override
   public User get(int userId) {
      return userDao.get(userId);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }
   
   @Transactional
   @Override
   public User checkUser(String username, String password) {
	   return userDao.checkUser(username, password);
   }

   @Transactional
   @Override
   public int changePass(String newPass, int userId) {
	   return userDao.changePass(newPass, userId);
   }
   
   @Transactional
   @Override
   public int checkUsername(String username) {
	   return userDao.checkUsername(username);
   }
}
