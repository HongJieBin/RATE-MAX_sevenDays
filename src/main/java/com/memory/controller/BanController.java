package com.memory.controller;


import com.memory.pojo.Ban;
import com.memory.pojo.User;
import com.memory.service.BanService;
import com.memory.service.IUserService;
import com.memory.utils.JsonResult;
import com.memory.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.Date;

@Controller
@RequestMapping(value = "ban")
public class BanController {

    @Autowired
    private BanService banService;

    @Autowired
    private IUserService iUserService;


    /**
     * 封禁用户（默认30天）
     * @param userId ：封禁的用户id
     * @return
     */
    @RequestMapping(value = "banUser",method = RequestMethod.POST)
    public @ResponseBody String ban(@RequestParam(name = "userId")int userId){
        try {
            User user = iUserService.get(userId);
            if(user == null)
                return JsonUtils.toJSON(JsonResult.errorMsg("该用户不存在"));
            Ban ban = new Ban();
            ban.setUser(user);
            ban.setBanStime(new Timestamp((new Date()).getTime()));
            ban.setBanEtime(new Timestamp(new Date().getTime()+30*24*60*60*1000));
            banService.ban(ban);
        }catch (Exception e){
            return JsonUtils.toJSON(JsonResult.errorException("服务器错误:"+e.getMessage()));
        }
        return JsonUtils.toJSON(JsonResult.ok("封禁成功"));
    }


    /**
     * 解封用户
     * @param userId：解封的用户id
     * @return
     */
    @RequestMapping(value = "disBan",method = RequestMethod.POST)
    public @ResponseBody String disBan(@RequestParam(name = "userId") int userId){
        Ban ban = null;
        try {
            ban = banService.getByUserId(userId);
            if(ban != null){
                banService.disBan(ban);
                return JsonUtils.toJSON(JsonResult.ok("解封成功"));
            }
        }catch (Exception e){
            e.printStackTrace();
            return JsonUtils.toJSON(JsonResult.errorException("服务器错误："+e.getMessage()));
        }
        return JsonUtils.toJSON(JsonResult.errorMsg("该用户没有被封禁"));
    }
}
