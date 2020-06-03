package com.memory.controller;


import com.alibaba.fastjson.JSONObject;
import com.memory.pojo.Ban;
import com.memory.pojo.User;
import com.memory.service.BanService;
import com.memory.service.UserService;
import com.memory.utils.JsonResult;
import com.memory.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/ban")
public class BanController {

    @Autowired
    private BanService banService;


   @Autowired
    private UserService userService;


    /**
     * 封禁用户（默认30天）
     * @param  ：封禁的用户id
     * @return
     */
    @RequestMapping(value = "banUser",method = RequestMethod.POST,produces = "application/json;charset = UTF-8")
    public @ResponseBody String ban(@RequestBody JSONObject json){
        try {
            banService.ban(json.getInteger("userId"));
        }catch (Exception e){
            return JsonUtils.toJSON(JsonResult.errorException("服务器错误:"+e.getMessage()));
        }
        return JsonUtils.toJSON(JsonResult.ok("封禁成功"));
    }


    /**
     * 解封用户
     * @param ：解封的用户id
     * @return
     */
    @RequestMapping(value = "disBan",method = RequestMethod.POST)
    public @ResponseBody String disBan(@RequestBody JSONObject jsonObject){
        Ban ban = null;
        try {
            List<Ban> list = banService.getByUserId(jsonObject.getInteger("userId"));
            for(Ban b : list)
                if(b.getBanEtime().after(new Timestamp(new Date().getTime())))
                    banService.disBan(b);
            return JsonUtils.toJSON(JsonResult.ok("解封成功！"));
        }catch (Exception e){
            e.printStackTrace();
            return JsonUtils.toJSON(JsonResult.errorException("服务器错误："+e.getMessage()));
        }
    }
}