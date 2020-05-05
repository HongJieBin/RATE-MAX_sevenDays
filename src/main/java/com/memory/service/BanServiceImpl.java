package com.memory.service;


import com.memory.dao.BanDAO;
import com.memory.pojo.Ban;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BanServiceImpl implements BanService {

    @Autowired
    private BanDAO banDAO;

    @Override
    public void ban(Ban ban) {
        banDAO.add(ban);
    }

    @Override
    public Ban getByUserId(int userId) {
        return banDAO.getByUserId(userId);
    }

    @Override
    public void disBan(Ban ban) {
        banDAO.delete(ban);
    }
}
