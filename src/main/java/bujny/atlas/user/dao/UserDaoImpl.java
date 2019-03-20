package bujny.atlas.user.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bujny.atlas.user.entity.User;


@Repository
public class UserDaoImpl implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

	@Override
	public User get(int userId) {
		@SuppressWarnings("unchecked")
		TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User WHERE userid= :userid");
		query.setParameter("userid",userId);
		return query.getSingleResult();
	}

	@Override
   public List<User> listUsers() {
      @SuppressWarnings("unchecked")
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }
   
   @Override
    public User checkUser(String username, String password) {
	   User user;
	   @SuppressWarnings("unchecked")
	   TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User U WHERE U.username = :username and U.password = :password");
	   query.setParameter("username", username);
	   query.setParameter("password", password);
	   try {
		   user = query.getSingleResult();
	   } catch (Exception e) {
		   user = null;
	   }
	   return user;
   }
   
   @Override
   public int changePass(String newPass, int userId) {
	   @SuppressWarnings("unchecked")
	   TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("update User set password = :newPass where userId = :userId");
	   query.setParameter("newPass", newPass);
	   query.setParameter("userId", userId);
	   return query.executeUpdate();
   }
   
   @Override
   public int checkUsername(String username) {
	   @SuppressWarnings("unchecked")
	   TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User U WHERE U.username = :username");
	   query.setParameter("username", username);
	   int result = -1;
	   try {
		   result = query.getSingleResult().getUserId();
	   } catch (Exception e) {
		   result = -1;
	   }
	   return result;
	}
}
