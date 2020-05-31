package com.memory.service;

import com.memory.formbean.DriftBean;
import com.memory.pojo.Drift;

import java.util.List;

public interface DriftService {

    DriftBean randomGetOne();
    void save(Drift drift)throws Exception;
    Drift get(int driftId);
    void delete(Drift drift);
    void update(Drift drift);
    List<Drift> getByUserId(int userId);
}
