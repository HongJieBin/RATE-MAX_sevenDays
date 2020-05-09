package com.memory.controller;

import com.memory.pojo.User;
import com.memory.service.UserServiceImpl;
import com.memory.utils.JsonResult;
import com.memory.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author hy
 * @date 2020/5/1 - 8:22
 */
@Controller
public class Login2 {

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected UserServiceImpl service;


    @ResponseBody
    @RequestMapping(value = "user/login2",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String login2 (@RequestBody User iuser){
        //这里只获得了手机号一项参数：;
        String telephone = iuser.getTelephone();

        User user = null;

        try {
            user = service.loginUser2(telephone);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonUtils.toJSON(JsonResult.errorMsg("用户名/手机号错误，不存在该用户！！"));
        }
        if(user==null){
            return JsonUtils.toJSON(JsonResult.errorMsg("用户名或密码有误"));//这与上面其实重复了，保险起见，留着
        }
        request.getSession().setAttribute("user", user);
        return JsonUtils.toJSON(JsonResult.ok(user));
    }
}
