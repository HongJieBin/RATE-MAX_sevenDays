package com.memory.service;

import com.memory.dao.CommentDAO;
import com.memory.dao.PostDAO;
import com.memory.dao.PostLikeDAO;
import com.memory.pojo.Comment;
import com.memory.pojo.Post;
import com.memory.pojo.PostLike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * @author hy
 * @date 2020/5/3 - 10:29
 */
@Service
public class CornerServiceImpl implements CornerService{

    @Autowired
    private PostDAO postDAO;

    @Autowired
    private CommentDAO commentDAO;

    @Autowired
    private PostLikeDAO postLikeDAO;

    @Override
    public void postTalk(Post post) throws Exception {
        postDAO.add(post);
    }

    @Override
    public void postComment(Comment comment) throws Exception {
        commentDAO.add(comment);
    }

    @Override
    public Post getPost(int userId) throws Exception {
        List<Post> postList=postDAO.get("User.user_id",userId);//根据post表中user外键的user_id获取post列表
        Random random = new Random();
        int n = random.nextInt(postList.size());
        return postList.get(n);   //返回一个随机的post对象
    }

    @Override
    public void postLike(PostLike postLike) throws Exception {
        //TODO
        postLikeDAO.add(postLike);
        int postId = postLike.getPostId();
        Post post = postDAO.get(postId);
        post.setPostLike(post.getPostLike()+1);
        postDAO.update(post);
    }

    @Override
    public void notpostLike(PostLike postLike) throws Exception {
        //TODO
        postLikeDAO.delete(postLike);
        int postId = postLike.getPostId();
        Post post = postDAO.get(postId);
        post.setPostLike(post.getPostLike()-1);
        postDAO.update(post);
    }

    @Override
    public PostLike likeOrNot(PostLike postLike) throws Exception {
        return postLikeDAO.select(postLike.getPostId(),postLike.getUserId());
    }

    @Override
    public List<Comment> getLatestComment(int start, int max, String postId) {
        List<Comment> Comments= commentDAO.getLatest(start, max,postId);
        return Comments;
    }
}
