package com.memory.controller.user;

import com.memory.pojo.User;
import com.memory.service.UserServiceImpl;
import com.memory.utils.JsonResult;
import com.memory.utils.JsonUtils;
import com.memory.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hy
 * @date 2020/4/28 - 13:29
 */

@Controller
public class LoginController {

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected UserServiceImpl service;

    @ResponseBody
    @RequestMapping(value = "/user/login",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String login (@RequestBody User iuser, HttpSession session){
        /*我自己的页面操作
        //获取用户填写的登录用户名//手机号
        String username = request.getParameter("username");
        //获取用户填写的登录密码
        String password = request.getParameter("password");
        //用户登录
        */
        String username = iuser.getTelephone();
        String password = null;

        System.out.println(iuser);

        User user = null;
        try {
            password= MD5Utils.getMD5Str(iuser.getPassword());
            user = service.loginUser(username,password);//数据库查询并返回用户信息
            if(user==null) {
                return JsonUtils.toJSON(JsonResult.errorMsg("用户名或密码有误"));
            }
            //重复登录问题：
            //session.getServletContext()得到时application对象
            ServletContext application=session.getServletContext();
            Map<String, String> loginMap = (Map<String, String>)application.getAttribute("loginMap");
            if(loginMap==null){
                loginMap = new HashMap<>();
            }
            for(String key:loginMap.keySet()) {
                if (user.getTelephone().equals(key)) {//用户名即为手机号  username == telephone
                    if(session.getId().equals(loginMap.get(key))) {
                        System.out.println(username+"在同一地点多次登录！");
                    }else{
                        System.out.println(username+"异地登录被拒绝！");
                        //session.setAttribute("tip", "该用户已经异地登录！");
                        return JsonUtils.toJSON(JsonResult.errorMsg("该用户已经异地登录！"));
                    }
                }
            }
            loginMap.put(user.getTelephone(),session.getId());
            application.setAttribute("loginMap", loginMap);
            session.setAttribute("username",user.getTelephone());
            System.out.println("登录成功！");
            return JsonUtils.toJSON(JsonResult.ok(user));
        } catch (Exception e) {
            e.printStackTrace();
            //System.out.println("用户名/手机号，密码问题");
            return JsonUtils.toJSON(JsonResult.errorMsg("用户名/手机号，用户名或密码错误"));
        }
        /*if(user==null){
            String message = String.format(
                    "对不起，用户名或密码有误！！请重新登录！秒后为您自动跳到登录页面！！<meta http-equiv='refresh' content=';url=%s'",
                    request.getContextPath()+"/servlet/LoginUIServlet");
            request.setAttribute("message",message);
            //request.getRequestDispatcher("/message.jsp").forward(request, response);
            return JsonUtils.toJSON(JsonResult.errorMsg("用户名或密码有误"));
        }*/
//登录成功后，就将用户存储到session中
        //request.getSession().setAttribute("user", user);
        /*String message = String.format(
                "恭喜：%s,登陆成功！本页将在秒后跳到首页！！<meta http-equiv='refresh' content=';url=%s'",
                user.getTelephone(),
                request.getContextPath()+"/index.jsp");
        request.setAttribute("message",message);
        //request.getRequestDispatcher("/message.jsp").forward(request, response);*/
        //return JsonUtils.toJSON(JsonResult.ok(user));
    }
}
