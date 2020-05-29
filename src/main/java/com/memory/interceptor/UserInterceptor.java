package com.memory.interceptor;

import com.alibaba.fastjson.JSON;
import com.memory.utils.JsonResult;
import com.memory.utils.JsonUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hy
 * @date 2020/5/28 - 16:16
 */
public class UserInterceptor implements HandlerInterceptor {


    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
        ServletContext application=request.getSession().getServletContext();
        Map<String, String> loginMap = (Map<String, String>)application.getAttribute("loginMap");
        if(loginMap==null) {
            //session被销毁或尚未登录
            //未登录的访问
            //重置response
            response.reset();
            //设置编码格式
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");

            PrintWriter pw = response.getWriter();

            pw.write(JsonUtils.toJSON(JsonResult.errorTokenMsg("尚未登录！")));
            pw.flush();
            pw.close();
            return false;
        }
        if(loginMap.isEmpty()){
            //loginMap中信息被清空，曾经登录或      登录失败过（登录失败之前会创建loginmap）
            response.reset();
            //设置编码格式
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");

            PrintWriter pw = response.getWriter();

            pw.write(JsonUtils.toJSON(JsonResult.errorTokenMsg("用户信息过期，重新登录！")));
            pw.flush();
            pw.close();
            return false;
        }
        //正常访问,看看session及里面的map键值对
        System.out.println(request.getSession());
        System.out.println(loginMap);
        return true;
    }

}
