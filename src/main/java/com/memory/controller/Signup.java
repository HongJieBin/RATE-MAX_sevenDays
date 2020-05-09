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
 * @date 2020/4/28 - 12:32
 */

@Controller
public class Signup {


    @Autowired
    protected HttpServletRequest request;


    @Autowired
    protected UserServiceImpl service;

    @ResponseBody()
    @RequestMapping(value = "/user/signup",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public String register (@RequestBody User iuser){

        //我自己页面的操作，这里简单起见先不做数据判断
        /*//将客户端提交的表单数据封装到RegisterFormBean对象中
        RegisterFormBean formbean = WebUtils.requestBean(request, RegisterFormBean.class);
        //校验用户注册填写的表单数据
        if (formbean.validate() == false) {//如果校验失败
            //将封装了用户填写的表单数据的formbean对象发送回register.jsp页面的form表单中进行显示
            request.setAttribute("formbean", formbean);
            //校验失败就说明是用户填写的表单数据有问题，那么就跳转回register.jsp
            //request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
            return JsonUtils.toJSON(JsonResult.errorMsg("填写的注册表单数据有问题"));
        }*/

        /*User user = new User();*/
        try {
            //这部分是自己测试页面将formbean对象放入User对象中

            // 注册字符串到日期的转换器
            //ConvertUtils.register(new DateLocaleConverter(), Date.class);
            /*BeanUtils.copyProperties(user, formbean);//把表单的数据填充到javabean中
            //user.setNickname(WebUtils.makeId());//设置用户的Id属性,随机生成用户的nickname，暂不使用此方法
            user.setNickname(formbean.getUserName());
            user.setTelephone(formbean.getTelephoneNumber());
            user.setPassword(formbean.getUserPwd());*/

            iuser.setProfile("无");

            //IUserService service = new UserServiceImpl();
            //调用service层提供的注册用户服务实现用户注册
            service.registerUser(iuser);
            /*String message = String.format(
                    "注册成功！！秒后为您自动跳到登录页面！！<meta http-equiv='refresh' content=';url=%s'/>",
                    request.getContextPath()+"/servlet/LoginUIServlet");
            request.setAttribute("message",message);
            request.getRequestDispatcher("/message.jsp").forward(request,response);*/
            return JsonUtils.toJSON(JsonResult.ok("注册成功，这里返回登录界面"));
        } /*catch (UserExistException e) {
            formbean.getErrors().put("userName", "注册用户已存在！！");
            request.setAttribute("formbean", formbean);
            request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
        } */catch (Exception e) {
            e.printStackTrace(); // 在后台记录异常
            request.setAttribute("message", "对不起，注册失败！！");
            //request.getRequestDispatcher("/message.jsp").forward(request,response);
            return JsonUtils.toJSON(JsonResult.errorMsg("后台记录异常，注册失败"));
        }
    }

}
