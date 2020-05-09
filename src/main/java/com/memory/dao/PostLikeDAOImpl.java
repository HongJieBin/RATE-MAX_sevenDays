package com.memory.dao;

import com.memory.pojo.Friend;
import com.memory.pojo.Post;
import com.memory.pojo.PostLike;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName PostLikeDAOImpl
 * @Description
 * @Author SupreacyXXXXX
 * @Date 2020/5/5
 * @Version 1.0
 **/

@Repository
@Transactional
public class PostLikeDAOImpl implements PostLikeDAO {
    @Resource
    private HibernateTemplate hibernateTemplate;

    @Override
    public void add(PostLike postLike) {
        hibernateTemplate.save(postLike);
    }

    @Override
    public void update(PostLike postLike) {
        hibernateTemplate.update(postLike);
    }

    @Override
    public void delete(PostLike postLike) {
        hibernateTemplate.delete(postLike);
    }

    @Override
    public List getByUserId(int userId) {
        String hql = "from PostLike as pl where pl.userId = ?";
        return (List<PostLike>) hibernateTemplate.find(hql, userId);
    }

    @Override
    public List getByPostId(int postId) {
        String hql = "from PostLike as pl where pl.postId = ?";
        return (List<PostLike>) hibernateTemplate.find(hql, postId);
    }

    @Override
    public PostLike select(int postId, int userId) {
        String postid = Integer.toString(postId);
        String userid = Integer.toString(userId);
        String hql = "from PostLike as pl where pl.postId = " + postid
                + "and pl.userId = " + userid;
       return (PostLike) hibernateTemplate.getSessionFactory().getCurrentSession().
               createQuery(hql).uniqueResult();
    }
}
