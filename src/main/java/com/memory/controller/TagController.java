package com.memory.controller;

import com.memory.pojo.Tag;
import com.memory.service.TagServiceImpl;
import com.memory.utils.JsonResult;
import com.memory.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping(value = "/tag")
public class TagController {

    @Autowired
    private TagServiceImpl tagService;


    /**
     * 获取标签，上限为十个，随机选取
     * @return
     */
    @RequestMapping(value = "/getTag",method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public @ResponseBody String getTag(){
        Random random = new Random();
        List<Tag> list;
        try {           //get all tags
            list = tagService.getAll();
            return JsonUtils.toJSON(JsonResult.ok(list));
        }catch (Exception e){
            return JsonUtils.toJSON(JsonResult.errorException("查询异常"));
        }
    }

    /**
     * 设置标签请求，以后可能会用到
     * @param tag
     * @return
     */
    @RequestMapping(value = "/setTag" , method = RequestMethod.POST)
    public @ResponseBody String setTag(@RequestBody Tag tag){
        try {
            tagService.add(tag);
        }catch (Exception e){
            return JsonUtils.toJSON(JsonResult.errorException(e.getMessage()));
        }
        return JsonUtils.toJSON(JsonResult.ok("添加成功"));
    }
}
