package com.memory.controller;

import com.memory.pojo.Post;
import com.memory.pojo.User;
import com.memory.service.CornerServiceImpl;
import com.memory.service.SquareService;
import com.memory.service.SquareServiceImpl;
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
 * @date 2020/5/3 - 10:07
 * 得到一条新动态
 */

@Controller
public class GetPostController {
    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected SquareService service;

    @RequestMapping(value = "/corner/getpost",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getPost(@RequestBody Post post)
    {
        Post result = null;
        try{
            result=service.getPostById(post.getPostId());
            return JsonUtils.toJSON(JsonResult.ok(result));
        }catch (Exception e){
            e.printStackTrace(); // 在后台记录异常
            return JsonUtils.toJSON(JsonResult.errorMsg("后台记录异常，获取动态失败"));
        }

    }
}
