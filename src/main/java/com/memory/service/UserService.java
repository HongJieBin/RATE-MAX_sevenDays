package com.memory.service;


import com.memory.dao.UserDAO;
import com.memory.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;


    public User get(Integer id){
        return userDAO.get(id);
    }

    public void update(User u){
        userDAO.update(u);
    }

    public List<User>getAll(){return userDAO.getAll();}
}
