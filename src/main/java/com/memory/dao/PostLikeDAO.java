package com.memory.dao;

import com.memory.pojo.PostLike;

import java.util.List;

/**
 * @InterfaceName PostLikeDAO
 * @Description
 * @Author SupreacyXXXXX
 * @Date 2020/5/5
 * @Version 1.0
 **/
public interface PostLikeDAO {
    void add(PostLike postLike);
    void update(PostLike postLike);
    void delete(PostLike postLike);
    List getByUserId(int userId);
    List getByPostId(int postId);
    PostLike select(int postId,int userId);
}
