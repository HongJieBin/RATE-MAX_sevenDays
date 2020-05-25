package com.memory.formbean;

import com.memory.pojo.Memory;
import com.memory.pojo.User;

import javax.jws.soap.SOAPBinding;

public class UserBean {

    private int userId;
    private String nickname;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public static UserBean memoryToUserBean(Memory memory){
        UserBean userBean = new UserBean();
        userBean.setUserId(memory.getUser().getUserId());
        userBean.setNickname(memory.getUser().getNickname());
        return userBean;
    }

    public static UserBean toUserBean(User user){
        UserBean userBean = new UserBean();
        userBean.setNickname(user.getNickname());
        userBean.setUserId(user.getUserId());
        return userBean;
    }
}
