package com.memory.dao;

import com.memory.pojo.Msg;
import com.memory.pojo.Post;

import java.util.List;

/**
 * @InterfaceName PostDAO
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/4/23
 * @Version 1.0
 **/
public interface PostDAO {
    void add(Post post);
    void update(Post post);
    void delete(Post post);
    Post get(int id);
    List<Post> getLatest(int start, int max);
}
