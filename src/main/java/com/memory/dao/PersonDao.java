package com.memory.dao;

import com.memory.pojo.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("personDao")
public class PersonDao {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * 保存对象
     *
     * @param p
     * @return
     */

    @Transactional
    public void save(Person p) {
//        sessionFactory.getCurrentSession().save(p);
        sessionFactory.getCurrentSession().save(p);
    }
    /**
     * 根据UserName来查找用户
     *
     * @param userName 用户名
     * @return Person
     */
    @Transactional
    public Person getPersonByUserName(String userName) {
        return (Person)sessionFactory.getCurrentSession().createQuery("from Person where userName = ?")
                .setParameter(0, userName).uniqueResult();
    }
}
