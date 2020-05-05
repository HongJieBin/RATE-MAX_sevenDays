package com.memory.service;

import com.memory.dao.UserDAO;
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
                //TODO


            } else {
            throw new Exception("用户名或密码错误！！");
            }
        }else{
                System.out.println("不存在该用户");
            }

        return loginuer;
    }

    public User get(Integer id){
        return userDAO.get(id);
    }

    public void update(User u){
        userDAO.update(u);
    }

    public List<User>getAll(){return userDAO.getAll();}
}
