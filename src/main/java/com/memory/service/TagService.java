package com.memory.service;

import com.memory.pojo.Tag;

import java.util.List;

public interface TagService {
    Tag get(int tid);
    Tag getByTagName(String name);
    List<Tag> getAll();
    void add(Tag tag);
}
