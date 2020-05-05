package com.memory.service;

import com.memory.dao.AdminDAO;
import com.memory.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDAO adminDAO;

    @Override
    public Admin logIn(Admin admin) throws Exception{
        Admin admin1 = adminDAO.getByName(admin.getAdminName());
        return admin1;
    }
}
