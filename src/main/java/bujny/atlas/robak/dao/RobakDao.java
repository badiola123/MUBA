package bujny.atlas.robak.dao;

import bujny.atlas.robak.entity.Robak;
import java.util.List;


public interface RobakDao {
	   void add(Robak robak);
	   void remove(Robak robak);
	   void removeAllOwnersRobaks(int userId);
	   Robak get(int robakId);
	   List<Robak> listRobaks();
	   List<Robak> listMyRobaks(int userId);
	}