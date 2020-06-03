package com.memory.service;


import com.memory.dao.TagDAO;
import com.memory.pojo.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService{

    @Autowired
    private TagDAO tagDAO;


    /**
     * 通过tagId获取tag
     * @param tid
     * @return
     */
    public Tag get(int tid){
        return tagDAO.get(tid);
    }

    public Tag getByTagName(String name){
        return tagDAO.getByName(name);
    }

    public List<Tag> getAll(){
        return tagDAO.getAll();
    }

    public void add(Tag tag){ tagDAO.add(tag);}

    @Override
    public void deleteTag(int tagId) throws Exception {
        Tag tag = tagDAO.get(tagId);
        if (tag == null)
            throw new Exception("该标签不存在！");
        tagDAO.delete(tag);
    }

}
