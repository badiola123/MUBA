package edu.eskola.muba.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.eskola.muba.user.dao.UserDao;
import edu.eskola.muba.user.entity.User;

@Service
public class UserServiceImpl implements UserService {

   @Autowired
   private UserDao userDao;

   @Transactional
   @Override
   public void add(User user) {
      userDao.add(user);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }
   
   @Transactional
   @Override
   public boolean checkUser(String username, String password) {
	   return userDao.checkUser(username, password);
   }
}
