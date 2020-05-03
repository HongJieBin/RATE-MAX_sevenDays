package com.memory.controller;


import com.alibaba.fastjson.JSONObject;
import com.memory.pojo.Bug;
import com.memory.service.BugServiceImpl;
import com.memory.utils.JsonResult;
import com.memory.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/bug")
public class BugController {

    @Autowired
    private BugServiceImpl bugService;

    /**
     * Bug反馈
     * @return
     */
    @RequestMapping(value = "/feedback",method = RequestMethod.POST)
    public @ResponseBody String feedback(@RequestBody JSONObject json){
        Bug bug = new Bug();
        bug.setBugContent(json.getString("content"));                   //build bug object
        try {
            bugService.save(bug);
            //System.out.println(bug.getBugId());
        }catch (Exception e){
            return JsonUtils.toJSON(JsonResult.build(1000,"请求参数错误",""));
        }
        return JsonUtils.toJSON(JsonResult.ok());
    }

    /**
     * 测试方法
     * @return
     *
    @RequestMapping(value = "/getBug",method = RequestMethod.POST)
    public @ResponseBody String bug(@RequestBody Bug bug){
        return JsonUtils.toJSON(JsonResult.ok(bugService.get(bug.getBugId())));
    }*/
}
