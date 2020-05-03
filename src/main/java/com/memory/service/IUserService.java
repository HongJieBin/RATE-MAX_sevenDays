package com.memory.service;

import com.memory.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hy
 * @date 2020/4/25 - 13:49
 */

public interface IUserService {
    /**
     * 提供注册服务
     * @param user
     *
     * @throws Exception
     * 注册失败会抛出异常
     */
    void registerUser(User user) throws Exception;
    /**
     * 提供账号密码登录服务
     * @param userphone    //手机号
     * @param userPwd   //密码
     * @return
     */
    User loginUser(String userphone, String userPwd) throws Exception;

    public User get(Integer id);

    public void update(User u);

    public List<User> getAll();
}
