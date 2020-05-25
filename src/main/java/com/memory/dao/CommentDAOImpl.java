package com.memory.dao;

import com.memory.pojo.Comment;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public List<Comment> getLatest(int start, int max, String postId) {
        String hql = "from Comment as p where p.post.postId = "+postId;
        return (List<Comment>)hibernateTemplate.getSessionFactory().getCurrentSession().
                createQuery(hql).setFirstResult(start).
                setMaxResults(max).list();
    }
}
