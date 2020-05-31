package com.memory.dao;

import com.memory.pojo.Drift;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName DriftDAOImpl
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/4/23
 * @Version 1.0
 **/

@Repository
@Transactional
public class DriftDAOImpl implements DriftDAO {
    @Resource
    private HibernateTemplate hibernateTemplate;

    @Override
    public void add(Drift drift) {
        hibernateTemplate.save(drift);
    }

    @Override
    public void update(Drift drift) {
        hibernateTemplate.update(drift);
    }

    @Override
    public void delete(Drift drift) {
        hibernateTemplate.delete(drift);
    }

    @Override
    public Drift get(int id) {
        return hibernateTemplate.get(Drift.class, id);
    }

    @Override
    public List<Drift> getAll() {
        return (List<Drift>)hibernateTemplate.find("from Drift");
    }

    public void save(Drift drift)throws Exception{
        hibernateTemplate.save(drift);
    }

    @Override
    public List<Drift> getByUserId(int userId) {
        return (List<Drift>)hibernateTemplate.find("from Drift as d where d.user.userId");
    }
}
