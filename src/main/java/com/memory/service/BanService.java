package com.memory.service;

import com.memory.pojo.Ban;

public interface BanService {

    void ban(Ban ban);

    Ban getByUserId(int userId);

    void disBan(Ban ban);
}
