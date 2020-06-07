package com.memory.service;


import com.memory.dao.BanDAO;
import com.memory.dao.UserDAO;
import com.memory.pojo.Ban;
import com.memory.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class BanServiceImpl implements BanService {

    @Autowired
    private BanDAO banDAO;

    @Autowired
    private UserDAO userDAO;

    private long ONEDAY = 24*60*60*1000;

    @Override
    public void ban(int userId) throws Exception{
        User user = userDAO.get(userId);
        if(user == null) throw  new Exception("该用户不存在！");
        List<Ban> list = banDAO.get("user.userId",userId);
        for(Ban b : list)
            if(b.getBanEtime().after(new Timestamp(new Date().getTime())))
                throw new Exception("该用户已被封禁！");
        Ban ban = new Ban();
        ban.setUser(user);
        ban.setBanStime(new Timestamp(new Date().getTime()));
        ban.setBanEtime(new Timestamp(new Date().getTime() + ONEDAY * 30));
        banDAO.add(ban);
    }


    @Override
    public void disBan(Ban ban) {
        banDAO.delete(ban);
    }

    @Override
    public List<Ban> getByUserId(int userId) {
        return banDAO.get("user.userId",userId);
    }
}
