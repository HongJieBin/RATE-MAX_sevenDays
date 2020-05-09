package com.memory.controller.corner;

import com.memory.pojo.Post;
import com.memory.service.SquareService;
import com.memory.utils.JsonResult;
import com.memory.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName SquareController
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/5/2
 * @Version 1.0
 **/


@Controller
@RequestMapping(value = "/square")
public class SquareController {

    @Autowired
    private SquareService squareService;

    @RequestMapping(value = "/find",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String getLatestPost(int start, int max){
        List<Post> posts = squareService.getLatestPost(start, max);
        return JsonUtils.toJSON(JsonResult.ok(posts));
    }
}
