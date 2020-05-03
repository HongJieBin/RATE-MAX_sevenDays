package com.memory.service;

import com.memory.dao.PostDAO;
import com.memory.pojo.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName SquareServiceImpl
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/5/2
 * @Version 1.0
 **/

@Service
@Transactional
public class SquareServiceImpl implements SquareService{
    @Autowired
    private PostDAO postDAO;

    @Override
    public List<Post> getLatestPost(int start, int max) {
        List<Post> posts= postDAO.getLatest(start, max);
        return posts;
    }

    @Override
    public Post getPostById(int postId) {
        Post resultPost = postDAO.get(postId);
        return resultPost;
    }
}
