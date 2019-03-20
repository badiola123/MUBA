package bujny.atlas.robak.service;

import bujny.atlas.robak.entity.Robak;

import java.util.List;

public interface RobakService {
    void add(Robak robak);
    Robak get(int robakId);
    List<Robak> listRobaks();
    List<Robak> listMyRobaks(int userId);
}