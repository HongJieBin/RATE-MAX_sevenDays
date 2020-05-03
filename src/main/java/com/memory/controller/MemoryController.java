package com.memory.controller;


import com.alibaba.fastjson.JSONObject;
import com.memory.pojo.Memory;
import com.memory.pojo.User;
import com.memory.service.IUserService;
import com.memory.service.MemoryService;
import com.memory.utils.JsonResult;
import com.memory.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.Date;

@RequestMapping(value = "memorry")
public class MemoryController {

    @Autowired
    private MemoryService memoryService;

    @Autowired
    private IUserService userService;

    /**
     * 删除十字记忆
     * @param json ：userId:用户id
     *                memoryId：十字记忆id
     * @return
     */
    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    public @ResponseBody String delete(@RequestBody JSONObject json){
        Memory memory = memoryService.get(json.getInteger("memoryId"));
        try {
            memoryService.delete(memory);
        }catch (Exception e){
            return JsonUtils.toJSON(JsonResult.errorException(e.getMessage()));
        }
        return JsonUtils.toJSON(JsonResult.ok("删除成功"));
    }


    /**
     * 保存十字记忆
     * @param json ：
     *             userId：用户id
     *             content：十字记忆内容
     * @return
     */
    @RequestMapping(value = "create",method = RequestMethod.POST)
    public @ResponseBody String create(@RequestBody JSONObject json){
        User user = userService.get(json.getInteger("userId"));
        Memory memory = new Memory();
        memory.setMemoryContent(json.getString("content"));
        memory.setUser(user);
        memory.setMemoryDate(new Timestamp((new Date()).getTime()));
        try {
            memoryService.add(memory);
        }catch (Exception e){
            return JsonUtils.toJSON(JsonResult.errorException(e.getMessage()));
        }
        return JsonUtils.toJSON(JsonResult.ok("保存成功"));
    }
}
