package com.memory.service;


import com.memory.dao.TagDAO;
import com.memory.pojo.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    @Autowired
    private TagDAO tagDAO;


    /**
     * 通过tagId获取tag
     * @param tid
     * @return
     */
    public Tag get(Integer tid){
        return tagDAO.get(tid);
    }

    public Tag getByTagName(String name){
        return tagDAO.getByName(name);
    }

    public List<Tag> getAll(){
        return tagDAO.getAll();
    }
}
