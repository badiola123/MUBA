package bujny.atlas.robak.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import bujny.atlas.robak.entity.Robak;

import javax.persistence.TypedQuery;
import java.util.List;


@Repository
public class RobakDaoImpl implements RobakDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(Robak robak) {
      sessionFactory.getCurrentSession().save(robak);
   }

	@Override
	public Robak get(int robakId) {
		@SuppressWarnings("unchecked")
		TypedQuery<Robak> query=sessionFactory.getCurrentSession().createQuery("from Robak WHERE robakid= :robakid");
		query.setParameter("robakid",robakId);
		return query.getSingleResult();
	}

	@Override
   public List<Robak> listRobaks() {
      @SuppressWarnings("unchecked")
      TypedQuery<Robak> query=sessionFactory.getCurrentSession().createQuery("from Robak");
      return query.getResultList();
   }

	@Override
	public List<Robak> listMyRobaks(int userId) {
		@SuppressWarnings("unchecked")
		TypedQuery<Robak> query=sessionFactory.getCurrentSession().createQuery("from Robak where owner = :userid");
		query.setParameter("userid",userId);
		return query.getResultList();
	}

}
