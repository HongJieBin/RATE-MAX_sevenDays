package com.memory.service;

import com.memory.pojo.Comment;
import com.memory.pojo.Post;
import com.memory.pojo.PostLike;
import com.memory.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hy
 * @date 2020/5/3 - 10:28
 */
@Service
public interface CornerService {


    /**
     * 提供发布动态服务
     * @param post
     * @throws Exception
     *
     * 发布动态失败会抛出异常
     *
     */
    public void postTalk(Post post) throws Exception;

    /**
     * 提供评论服务
     * @param comment
     * @throws Exception
     */
    public void postComment(Comment comment) throws Exception;

    /**
     * 提供获取用户自己的一条随机评论
     * @param userId
     * @return
     * @throws Exception
     */
    public Post getPost(int userId) throws Exception;

    /**
     * 点个赞
     * @param postLike
     * @throws Exception
     */
    public void postLike(PostLike postLike) throws Exception;

    /**
     * 取消点赞
     * @param postLike
     * @throws Exception
     */
    public void notpostLike(PostLike postLike) throws Exception;

    /**
     * 查询是否点过赞
     * @param postLike
     * @return
     * @throws Exception
     */
    public PostLike likeOrNot(PostLike postLike) throws Exception;

    public List<Comment> getLatestComment(int start, int max, String postId) throws Exception;
}
