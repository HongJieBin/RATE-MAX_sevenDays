package com.memory.service;

import com.memory.dao.PostDAO;
import com.memory.pojo.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDAO postDAO;

    @Override
    public void delete(int postId) throws Exception{
        Post post = postDAO.get(postId);
        if(post == null) throw new Exception("该动态不存在！");
        postDAO.delete(post);
    }
}
