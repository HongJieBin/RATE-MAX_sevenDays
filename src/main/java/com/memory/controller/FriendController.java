package com.memory.controller;

import com.memory.dao.FriendDAO;
import com.memory.dao.ReportDAO;
import com.memory.pojo.Friend;
import com.memory.pojo.Report;
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
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Controller
@Transactional()
public class FriendController {
   @Autowired
   private FriendService friendService;
   @Autowired
   private ReportDAO reportDAO;

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
        friendService.saveFriends(friendUserId, userId);
        List<User> myFirends = (List<User>) friendService.queryFriendsList(userId);
        return JsonUtils.toJSON(JsonResult.ok(myFirends));
    }

    @RequestMapping(value = "Friend/delete/",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public  String deleteFriend(int userId,int deleteId) {
        friendService.deleteFriend(userId, deleteId);
        friendService.deleteFriend(deleteId,userId);
        List<User> myFirends = (List<User>) friendService.queryFriendsList(userId);
        return JsonUtils.toJSON(JsonResult.ok(myFirends));
    }


    @RequestMapping(value = "/Friend/getInterest",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String recommendFriends(int userId){
        List<User> recommendFriends = friendService.recommendFriends(userId);
        return JsonUtils.toJSON(JsonResult.ok(recommendFriends));
    }

    @RequestMapping(value = "/Friend/trust",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String trustFriend(int userId,int trustId){
        friendService.saveLevel(userId,trustId);
        return JsonUtils.toJSON(JsonResult.ok());
    }

    @RequestMapping(value = "/Friend/remark/",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String addRemark(int remarkId,int userId,String remark){
        return JsonUtils.toJSON(friendService.addRemark(userId,remarkId,remark));
    }

    @RequestMapping(value = "/Friend/getInformation/",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String getInformation(int userId,int viewedId){
        System.out.println("1111");
        System.out.println(JsonUtils.toJSON(friendService.getInformation(userId,viewedId)));
        return JsonUtils.toJSON(friendService.getInformation(userId,viewedId));
    }

    @RequestMapping(value = "Friend/report", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String reportUser(@RequestBody Report report){
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        report.setReportDate(timestamp);
        reportDAO.add(report);
        return JsonUtils.toJSON(JsonResult.ok());
    }
}
