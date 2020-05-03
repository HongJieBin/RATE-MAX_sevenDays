package com.memory.service;

import com.memory.pojo.Blacklist;
import com.memory.pojo.User;

import javax.jws.soap.SOAPBinding;
import java.util.List;

public interface BlacklistService {

    void add(Blacklist blacklist);
    void delete(Blacklist blacklist);
    Blacklist get(int userId,int addedId);
    List<Blacklist> getByUserId(int userId);

}
