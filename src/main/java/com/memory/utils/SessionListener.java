package com.memory.utils;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Map;

/**
 * @author hy
 * @date 2020/5/23 - 12:32
 * 当session关闭，注销用户信息
 */
public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent event) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        //在session销毁的时候 把loginMap中保存的键值对清除
        String username = event.getSession().getAttribute("username").toString();
        if(username!=null){
            Map<String, String> loginMap = (Map<String, String>)event.getSession().getServletContext().getAttribute("loginMap");
            loginMap.remove(username);
            event.getSession().getServletContext().setAttribute("loginMap",loginMap);
            System.out.println(username+"用户注销！");
        }
    }
}
