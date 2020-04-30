package com.memory.dao;

import com.memory.pojo.Comment;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @ClassName CommentDAOImpl
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/4/23
 * @Version 1.0
 **/

@Repository
@Transactional
public class CommentDAOImpl implements CommentDAO{
    @Resource
    private HibernateTemplate hibernateTemplate;

    @Override
    public void add(Comment comment) {
        hibernateTemplate.save(comment);
    }

    @Override
    public void update(Comment comment) {
        hibernateTemplate.update(comment);
    }

    @Override
    public void delete(Comment comment) {
        hibernateTemplate.delete(comment);
    }

    @Override
    public Comment get(int id) {
        return hibernateTemplate.get(Comment.class, id);
    }
}
