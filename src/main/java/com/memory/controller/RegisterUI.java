package com.memory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author hy
 * @date 2020/4/28 - 12:33
 */
@Controller
public class RegisterUI {

    @RequestMapping("/RegisterUI")
    public String showRegisterUI(Model model){
        return "register";
    }


    @RequestMapping("/LoginUI")
    public String showLoginUI(Model model){
        return "login";
    }


}
