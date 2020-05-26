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
 * @date 2020/5/1 - 9:39
 */
@Controller
public class ForgetRegister {

    @Autowired
    protected HttpServletRequest request;


    @Autowired
    protected UserServiceImpl service;

    @ResponseBody()
    @RequestMapping(value = "/user/forgetRegister",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String forgetregister (@RequestBody User iuser){
        //忘记密码的登录，前端页面只有传递手机号和密码，所以，要进入数据库需要先设置profile和nickname属性必要属性
        try{
            service.forgetRegister(iuser.getTelephone(),iuser.getPassword());
            return JsonUtils.toJSON(JsonResult.ok("修改密码成功，这里返回登录界面"));
        }catch (Exception exception){
            //request.setAttribute("message", "对不起，修改密码失败！！");
            return JsonUtils.toJSON(JsonResult.errorMsg("后台记录异常，注册失败"));
        }
    }
}
