package com.memory.dao;

import com.memory.pojo.Bug;
import com.memory.pojo.Comment;

import java.util.List;

/**
 * @InterfaceName CommentDAO
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/4/23
 * @Version 1.0
 **/
public interface CommentDAO {
    void add(Comment comment);
    void update(Comment comment);
    void delete(Comment comment);
    Comment get(int id);
    List<Comment> getLatest(int start, int max, String postId);
}
