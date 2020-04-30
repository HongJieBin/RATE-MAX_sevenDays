package com.memory.dao;

import com.memory.pojo.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName UserDAOImpl
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/4/21
 * @Version 1.0
 **/

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    /*
     *@Author: SupremacyXXXXX on 2020/4/21
     *@Param: User对象
     *@Return: void
     *@Description: 添加用户
     */
    @Override
    public void add(User user) {
        hibernateTemplate.save(user);
    }

    /*
     *@Author: SupremacyXXXXX on 2020/4/22
     *@Param: User对象
     *@Return: void
     *@Description: 更新用户信息，若表中不存在传入user的userId,则报错
     */
    @Override
    public void update(User user) {
        hibernateTemplate.update(user);
    }

    /*
     *@Author: SupremacyXXXXX on 2020/4/22
     *@Param: user对象
     *@Return: void
     *@Description: 删除数据库中对应的user对象
     */
    @Override
    public void delete(User user) {

        hibernateTemplate.delete(user);
    }

    /*
     *@Author: SupremacyXXXXX on 2020/4/22
     *@Param: User的userId
     *@Return: User对象
     *@Description: 根据id返回一个User对象
     */
    @Override
    public User get(int id) {
        return hibernateTemplate.get(User.class, id);
    }

    /*
     *@Author: SupremacyXXXXX on 2020/4/22
     *@Param: param : 查找的属性名（如nickname,telephone)  value:查找的属性值
     *@Return: User的List Collection
     *@Description: 根据传入的属性名与对应属性值进行查找
     */
    @Override
    public List<User> get(String param, String value) {
        String hql = "from User as u where u." + param + " = ?";
        return (List<User>) hibernateTemplate.find(hql, value);
    }

}
