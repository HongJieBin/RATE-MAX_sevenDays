package com.memory.controller;

import com.memory.pojo.User;
import com.memory.service.IUserService;
import com.memory.service.UserServiceImpl;
import com.memory.utils.JsonResult;
import com.memory.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author hy
 * @date 2020/4/28 - 13:29
 */

@Controller
public class LoginController {

    @Autowired
    protected UserServiceImpl service;

    @ResponseBody
    @RequestMapping(value = "/Login",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String login (@RequestBody User iuser,HttpServletRequest request){
        /*我自己的页面操作
        //获取用户填写的登录用户名//手机号
        String username = request.getParameter("username");
        //获取用户填写的登录密码
        String password = request.getParameter("password");
        //用户登录
        */
        String username = iuser.getTelephone();
        String password = iuser.getPassword();

        User user = null;
        try {
            user = service.loginUser(username,password);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("用户名/手机号，密码问题");
            return JsonUtils.toJSON(JsonResult.errorMsg("用户名/手机号，密码问题"));
        }
        if(user==null){
            /*String message = String.format(
                    "对不起，用户名或密码有误！！请重新登录！秒后为您自动跳到登录页面！！<meta http-equiv='refresh' content=';url=%s'",
                    request.getContextPath()+"/servlet/LoginUIServlet");
            request.setAttribute("message",message);
            //request.getRequestDispatcher("/message.jsp").forward(request, response);*/
            return JsonUtils.toJSON(JsonResult.errorMsg("用户名或密码有误"));
        }
//登录成功后，就将用户存储到session中
        request.getSession().setAttribute("user", user);
        /*String message = String.format(
                "恭喜：%s,登陆成功！本页将在秒后跳到首页！！<meta http-equiv='refresh' content=';url=%s'",
                user.getTelephone(),
                request.getContextPath()+"/index.jsp");
        request.setAttribute("message",message);
        //request.getRequestDispatcher("/message.jsp").forward(request, response);*/
        return JsonUtils.toJSON(JsonResult.errorMsg("登录成功"));
    }
}
