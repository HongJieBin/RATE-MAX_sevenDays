package com.memory.controller;


import com.memory.formbean.BlackListBean;
import com.memory.pojo.Blacklist;
import com.memory.pojo.User;
import com.memory.service.BlacklistServiceImpl;
import com.memory.service.UserService;
import com.memory.utils.JsonResult;
import com.memory.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping(value = "/blacklist")
public class BlacklistController {

    @Autowired
    private BlacklistServiceImpl blacklistService;

    @Autowired
    private UserService userService;


    /**
     * 将用户添加到黑名单中
     * @param
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public @ResponseBody String add(@RequestBody Blacklist blist){
        Blacklist blacklist;
        try {
            blacklist = blacklistService.get(blist.getUserId(),blist.getAddedId());
        }catch (Exception e){
            return JsonUtils.toJSON(JsonResult.errorException("查询错误"));
        }
        if(blacklist != null)
            return JsonUtils.toJSON(JsonResult.build(1000,"请求参数错误","该用户已在你的黑名单中"));
        else{
            blacklist = new Blacklist();
            blacklist.setUserId(blist.getUserId());
            blacklist.setAddedId(blist.getAddedId());
            try {
                blacklistService.add(blacklist);
            }catch (Exception e){
                return JsonUtils.toJSON(JsonResult.errorException("服务器错误"));
            }
        }
        return JsonUtils.toJSON(JsonResult.ok());
    }

    /**
     * 从黑名单中移除
     * @param
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public @ResponseBody String delete(@RequestBody Blacklist b){
        Blacklist blacklist;
        try {
            blacklist = blacklistService.get(b.getUserId(),b.getAddedId());
        }catch (Exception e){
            return JsonUtils.toJSON(JsonResult.errorException("服务器错误"));
        }
        if(blacklist == null){
            return JsonUtils.toJSON(JsonResult.build(1000,"请求参数错误","该用户不在你的黑名单中"));
        }else{
            blacklistService.delete(blacklist);
        }
        return JsonUtils.toJSON(JsonResult.ok());
    }


    /**
     * 获取用户的黑名单
     * @param user  用户id
     * @return  一个用户黑名单的id，昵称键值对map
     */
    @RequestMapping(value = "getBlackList",method = RequestMethod.POST)
    public @ResponseBody String getBlackList(@RequestBody User user){
        List<BlackListBean> blist = new LinkedList<>();
        try {
            List<Blacklist> list = blacklistService.getByUserId(user.getUserId());
            if(list != null){
                for(Blacklist l : list){
                    User u = userService.get(l.getAddedId());
                    BlackListBean blackListBean = new BlackListBean();
                    blackListBean.setUserId(u.getUserId());
                    blackListBean.setNickname(u.getNickname());
                    blist.add(blackListBean);
                }
            }
        }catch (Exception e){
            return JsonUtils.toJSON(JsonResult.errorException("error:"+e.getMessage()));
        }
        return JsonUtils.toJSON(JsonResult.ok(blist));
    }

    /**
     * 测试方法
     * @param blacklist
     * @return
     *
     @RequestMapping(value = "getBlackList",method = RequestMethod.POST)
     public @ResponseBody String getBlackList(@RequestBody Blacklist blacklist){
     return JsonUtils.toJSON(JsonResult.ok(blacklistService.get(blacklist.getUserId(),blacklist.getAddedId())));
     }*/
}
