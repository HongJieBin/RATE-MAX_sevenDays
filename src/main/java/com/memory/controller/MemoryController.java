package com.memory.controller;


import com.alibaba.fastjson.JSONObject;
import com.memory.pojo.Memory;
import com.memory.pojo.User;
import com.memory.service.IUserService;
import com.memory.service.MemoryService;
import com.memory.utils.JsonResult;
import com.memory.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.Date;

@Controller
@RequestMapping(value = "memory")
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
        memory.setMemoryTitle(json.getString("memoryTitle"));
        memory.setMemoryDate(new Timestamp((new Date()).getTime()));
        try {
            memoryService.add(memory);
        }catch (Exception e){
            return JsonUtils.toJSON(JsonResult.errorException(e.getMessage()));
        }
        return JsonUtils.toJSON(JsonResult.ok("保存成功"));
    }


    /**
     * 修改十字记忆
     * @param memory：封装参数：
     *                  memoryId：十字记忆id
     *                  memoryTitle：新的十字记忆标题（可选）
     *                  memoryContent：新的十字记忆内容
     * @return
     */

    @RequestMapping(value = "edit",method = RequestMethod.POST)
    public @ResponseBody String edit(@RequestBody Memory memory){
        Memory m;
        try {
            m = memoryService.get(memory.getMemoryId());
        }catch (Exception e){
            return JsonUtils.toJSON(JsonResult.errorException("查询错误:"+e.getMessage()));
        }
        if(m == null){
            return JsonUtils.toJSON(JsonResult.errorMsg("该十字记忆不存在，id："+ memory.getMemoryId()));
        }
        m.setMemoryContent(memory.getMemoryContent());
        m.setMemoryDate(new Timestamp((new Date()).getTime()));
        if( memory.getMemoryTitle() != null && memory.getMemoryTitle().length() != 0)
            m.setMemoryTitle(memory.getMemoryTitle());
        try {
            memoryService.update(m);
        }catch (Exception e){
            return JsonUtils.toJSON(JsonResult.errorException("保存错误:"+e.getMessage()));
        }
        return JsonUtils.toJSON(JsonResult.ok("保存成功！"));
    }
}
