package bujny.atlas.robak.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import bujny.atlas.robak.dao.RobakDao;
import bujny.atlas.robak.entity.Robak;

import java.util.List;


@Service
public class RobakServiceImpl implements RobakService {

   @Autowired
   private RobakDao robakDao;

   @Transactional
   @Override
   public void add(Robak robak) {
      robakDao.add(robak);
   }

   @Transactional
   @Override
   public void remove(Robak robak) {
      robakDao.remove(robak);
   }

   @Transactional
   @Override
   public void removeAllOwnersRobaks(int userId) {
      robakDao.removeAllOwnersRobaks(userId);
   }

   @Transactional
   @Override
   public Robak get(int robakId) {
      return robakDao.get(robakId);
   }

   @Transactional
   @Override
   public List<Robak> listRobaks() {
      return robakDao.listRobaks();
   }

   @Transactional
   @Override
   public List<Robak> listMyRobaks(int robakId) {
      return robakDao.listMyRobaks(robakId);
   }

}
