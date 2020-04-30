package com.memory.controller;

import com.memory.dao.FriendDAO;
import com.memory.pojo.Friend;
import com.memory.pojo.User;
import com.memory.service.FriendService;
import com.memory.utils.JsonResult;
import com.memory.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
@Transactional()
public class FriendController {
   @Autowired
    private FriendService friendService;

    @RequestMapping(value="/Friends",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public @ResponseBody String getFriends(int userId) {
        if (!friendService.isExitUser(userId)) {
            return JsonUtils.toJSON(JsonResult.errorMsg("不存在此用户"));
        }
        else {
            // 1. 数据库查询好友列表
            List<User> myFirends = (List<User>) friendService.queryFriendsList(userId);
            return JsonUtils.toJSON(JsonResult.ok(myFirends));
        }
    }

    @RequestMapping(value = "Friend/add",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String addFriends(int userId, int friendUserId){
        friendService.saveFriends(userId, friendUserId);
        List<User> myFirends = (List<User>) friendService.queryFriendsList(userId);
        return JsonUtils.toJSON(JsonResult.ok(myFirends));
    }

    @RequestMapping(value = "Friend/delete/",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public  String deleteFriend(int userId,int deleteId) {
        friendService.deleteFriend(userId,deleteId);
        List<User> myFirends = (List<User>) friendService.queryFriendsList(userId);
        return JsonUtils.toJSON(JsonResult.ok(myFirends));
    }

}
