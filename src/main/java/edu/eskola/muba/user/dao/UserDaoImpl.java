package edu.eskola.muba.user.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.eskola.muba.user.entity.User;

@Repository
public class UserDaoImpl implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   public List<User> listUsers() {
      @SuppressWarnings("unchecked")
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }
   
   @Override
   public boolean checkUser(String username, String password) {
	   @SuppressWarnings("unchecked")
	   TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User U WHERE U.username ='"+username+"' and U.password ="+password);
	   User user = query.getSingleResult();
	   return user!=null ? true : false;
   }
}
