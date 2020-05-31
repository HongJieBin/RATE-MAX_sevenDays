package com.memory.service;

import com.memory.dao.BanDAO;
import com.memory.dao.UserDAO;
import com.memory.pojo.Ban;
import com.memory.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hy
 * @date 2020/4/25 - 13:53
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;
    //private UserDAO userDAO = new UserDAOImpl();

    @Autowired
    private BanDAO banDAO;


    @Override
    public void registerUser(User user) throws Exception {
        if(userDAO.get("telephone",user.getTelephone()).size()==1) {
            //checked exception
            //unchecked exception
            //抛编译时异常的原因：上一层程序处理这个异常，以给用户一个友好提示
            System.out.println(user.getTelephone());
            System.out.println(userDAO.get("telephone",user.getTelephone()));

            throw new Exception("注册的用户名已存在！！！");
        }
        userDAO.add(user);
    }

    @Override
    public User loginUser(String userphone, String userPwd) throws Exception {
        //查询电话号
        User loginuer = null;
        List<User> userList = userDAO.get("telephone",userphone);
        if(userList.size()==1){
            loginuer = userList.get(0);
            if (loginuer.getPassword().equals(userPwd)) {
                System.out.println("登录成功，去往首页");
            } else {
                throw new Exception("用户名或密码错误！！");
            }
        }else{
            System.out.println("不存在该用户");
        }

        return loginuer;
    }

    @Override
    public User loginUser2(String telephone) throws Exception {
        //查询电话号
        User loginuser = null;
        System.out.println(telephone);
        List<User> userList = userDAO.get("telephone",telephone);
        if(userList.size()==1){
            loginuser = userList.get(0);
            System.out.println("登陆成功，去往首页");
        }else{
            throw new Exception("用户名/手机号错误，不存在该用户！！");//手机验证码在前端校验
        }
        return loginuser;
    }

    @Override
    public void forgetRegister(String telephone, String password) throws Exception {
        //重新设置密码
        User forgetuser = null;
        List<User> userList = userDAO.get("telephone",telephone);
        if(userList.size()==1){
            forgetuser = userList.get(0);
            forgetuser.setPassword(password);
            userDAO.update(forgetuser);
            System.out.println("修改密码成功，账号：" + telephone + ",密码：" +password);
        }else{
            throw new Exception("用户名/手机号未注册！！");
        }
    }

    @Override
    public boolean userIsLocked(String username) throws Exception {
        List<User> userList= userDAO.get("telephone",username);
        List<Ban> banList=banDAO.get("user.userId",userList.get(0).getUserId());
        if(banList.size()==1){
            return true;
        }
        return false;
    }

    public User get(Integer id){
        return userDAO.get(id);
    }

    public void update(User u){
        userDAO.update(u);
    }

    public List<User>getAll(){return userDAO.getAll();}


}

