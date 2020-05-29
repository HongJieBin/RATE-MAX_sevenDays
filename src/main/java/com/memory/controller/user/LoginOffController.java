package com.memory.controller.user;

import com.memory.pojo.User;
import com.memory.utils.JsonResult;
import com.memory.utils.JsonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author hy
 * @date 2020/5/23 - 13:42
 */
@Controller
public class LoginOffController {

    @ResponseBody
    @RequestMapping(value = "/user/loginoff",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String loginoff (HttpSession session){
        session.invalidate();
        return JsonUtils.toJSON(JsonResult.ok("注销完成"));
    }
}
