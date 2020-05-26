package com.memory.service;

import com.memory.pojo.Post;

import java.util.List;

/**
 * @ClassName SquareService
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/5/2
 * @Version 1.0
 **/


public interface SquareService {
    public List<Post> getLatestPost(int start, int max);
    public Post getPostById(int postId);
}
