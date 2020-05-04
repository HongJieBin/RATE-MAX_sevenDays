package com.memory.controller;

import com.memory.pojo.Admin;
import com.memory.service.AdminService;
import com.memory.service.IUserService;
import com.memory.service.UserTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private IUserService iUserService;

    @RequestMapping(value = "logIn",method = RequestMethod.POST)
    public String logIn(@RequestParam(name = "adminName")String name,@RequestParam(name = "adminPassword")String password, HttpSession session, HttpServletRequest request){
        Admin admin1 = null;
        Admin admin = new Admin();
        admin.setAdminName(name);
        admin.setAdminPassword(password);
        try {
            admin1 = adminService.logIn(admin);
            if(admin1 != null){
                if(admin1.getAdminPassword().equals(admin.getAdminPassword())){
                    session.setAttribute("adminId",admin1.getAdminId());
                    session.setAttribute("adminName",admin1.getAdminName());
                    request.setAttribute("code",200);
                    request.setAttribute("msg","登录成功！");
                    request.setAttribute("users",iUserService.getAll());
                    return "listUser";
                }else {
                    System.out.println("密码错误");
                    request.setAttribute("code",2008);
                    request.setAttribute("msg","密码错误");
                    request.setAttribute("users",null);
                    return "adminLogin";
                }
            }
        }catch (Exception e){
            System.out.println("发生异常");
            e.printStackTrace();
            System.out.println("error");
            request.setAttribute("code",1999);
            request.setAttribute("msg","服务器错误");
            request.setAttribute("users",null);
            return "adminLogin";
        }
        System.out.println("管理员不存在");
        request.setAttribute("code",2008);
        request.setAttribute("msg","该管理员不存在");
        request.setAttribute("users",null);
        return "adminLogin";
    }
}
