package com.memory.service;


import com.memory.dao.DriftDAO;
import com.memory.formbean.DriftBean;
import com.memory.pojo.Drift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class DriftServiceImpl implements DriftService{


    @Autowired
    private DriftDAO driftDAO;


    @Override
    public DriftBean randomGetOne() {
        Random random = new Random();
        List<Drift> list = driftDAO.getAll();
        Drift drift;
        do {
            drift = list.get(random.nextInt(list.size()));
        }while(drift.getFlag() == 1);
        return DriftBean.toDriftBean(drift);
    }

    @Override
    public void save(Drift drift) throws Exception{
        driftDAO.save(drift);
    }

    @Override
    public Drift get(int driftId) {
        return driftDAO.get(driftId);
    }

    @Override
    public void delete(Drift drift) {
        driftDAO.delete(drift);
    }

    @Override
    public void update(Drift drift) {
        driftDAO.update(drift);
    }

    @Override
    public List<Drift> getByUserId(int userId) {
        return driftDAO.getByUserId(userId);
    }
}
