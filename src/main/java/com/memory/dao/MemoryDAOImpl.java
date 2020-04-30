package com.memory.dao;

import com.memory.pojo.Memory;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @ClassName MemoryDAOImpl
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/4/22
 * @Version 1.0
 **/

@Repository
@Transactional
public class MemoryDAOImpl implements MemoryDAO {
    @Resource
    private HibernateTemplate hibernateTemplate;

    @Override
    public void add(Memory memory) {
        hibernateTemplate.save(memory);
    }

    @Override
    public void update(Memory memory) {
        hibernateTemplate.update(memory);
    }

    @Override
    public void delete(Memory memory) {
        hibernateTemplate.delete(memory);
    }

    @Override
    public Memory get(int id) {
        return hibernateTemplate.get(Memory.class, id);
    }
}
