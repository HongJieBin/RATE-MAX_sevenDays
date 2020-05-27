package com.memory.controller;

import com.memory.pojo.Chatroom;
import com.memory.service.ChatroomService;
import com.memory.utils.JsonResult;
import com.memory.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @ClassName ChatroomController
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/5/26
 * @Version 1.0
 **/


@Controller
public class ChatroomController {
    @Autowired
    private ChatroomService chatroomService;

    @RequestMapping(value = "/chatRoom/create", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String createChatroom(@RequestBody Chatroom chatroom){
        System.out.println(chatroom);
        chatroomService.addChatroom(chatroom);
        return JsonUtils.toJSON(JsonResult.ok());
    }

    @RequestMapping(value = "/chatRoom/delete", method = RequestMethod.DELETE, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String deleteChatroom(int chatroomId){
        boolean result = chatroomService.deleteChatroomById(chatroomId);
        if (result){
            return JsonUtils.toJSON(JsonResult.ok());
        } else {
            return JsonUtils.toJSON(JsonResult.errorMsg("删除失败"));
        }
    }
}
