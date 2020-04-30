package com.memory.dao;

import com.memory.pojo.Post;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @ClassName PostDAOImpl
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/4/23
 * @Version 1.0
 **/

@Repository
@Transactional
public class PostDAOImpl implements PostDAO{
    @Resource
    private HibernateTemplate hibernateTemplate;

    @Override
    public void add(Post post) {
        hibernateTemplate.save(post);
    }

    @Override
    public void update(Post post) {
        hibernateTemplate.update(post);
    }

    @Override
    public void delete(Post post) {
        hibernateTemplate.delete(post);
    }

    @Override
    public Post get(int id) {
        return hibernateTemplate.get(Post.class, id);
    }
}
