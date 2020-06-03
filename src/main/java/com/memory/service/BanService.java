package com.memory.service;

import com.memory.pojo.Ban;

import java.util.List;

public interface BanService {

    void ban(int userId) throws Exception;

    void disBan(Ban ban);

    List<Ban> getByUserId(int userId);
}
