package com.memory.controller.user;

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

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hy
 * @date 2020/5/1 - 8:22
 */
@Controller
public class Login2Controller {

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected UserServiceImpl service;


    @ResponseBody
    @RequestMapping(value = "user/login2",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String login2 (@RequestBody User iuser, HttpSession session){
        //这里只获得了手机号一项参数：;
        String username = iuser.getTelephone();

        User user = null;

        try {
            user = service.loginUser2(username);
            if(user==null){
                return JsonUtils.toJSON(JsonResult.errorMsg("用户名/手机号不存在"));
            }
            //封禁问题：
            if(service.userIsLocked(username)){
                return JsonUtils.toJSON(JsonResult.errorMsg("用户被锁"));
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
                        return JsonUtils.toJSON(JsonResult.errorMsg("同一地点重复登录！"));
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
            return JsonUtils.toJSON(JsonResult.errorMsg("用户名/手机号错误，不存在该用户！！"));
        }
    }
}
