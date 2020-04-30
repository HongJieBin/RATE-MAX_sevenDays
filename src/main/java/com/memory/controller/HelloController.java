package com.memory.controller;

import com.memory.dao.PersonDao;
import com.memory.utils.JsonResult;
import com.memory.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @Autowired
    PersonDao personDao;

    @RequestMapping("/hello")
    public @ResponseBody String hello(int id) {
        return JsonUtils.toJSON(JsonResult.ok(id));
    }
}
