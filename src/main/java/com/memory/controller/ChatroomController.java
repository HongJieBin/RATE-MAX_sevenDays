package com.memory.controller;

import com.memory.controller.VO.ChatRoomVO;
import com.memory.controller.VO.ChatroomInfoVo;
import com.memory.pojo.Chatroom;
import com.memory.pojo.User;
import com.memory.service.ChatroomService;
import com.memory.utils.JsonResult;
import com.memory.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;




@Controller
public class ChatroomController {
    @Autowired
    private ChatroomService chatroomService;


    @RequestMapping(value = "/chatRoom/searchById",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String searchById(@RequestBody Chatroom chatroom){
        return chatroomService.searchById(chatroom.getChatroomId());
    }

    @RequestMapping(value = "/chatRoom/searchByTag",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String searchByTag(@RequestBody Chatroom chatroom){
        return chatroomService.searchByTag(chatroom.getChatroomTag());
    }

    @RequestMapping(value = "/chatRoom/searchByName",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String searchByName(@RequestBody Chatroom chatroom){
        return chatroomService.searchByName(chatroom.getChatroomName());
    }

    @RequestMapping(value = "/chatRoom/add",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String add(@RequestBody Chatroom chatroom,User user){
        try{
            if(!chatroomService.isExistChatRoom(chatroom.getChatroomId()))  return JsonUtils.toJSON(JsonResult.errorMsg("不存在此聊天室"));
            else if(chatroomService.isInChatRoom(user.getUserId(),chatroom.getChatroomId())) return JsonUtils.toJSON(JsonResult.errorMsg("你已经在聊天室内"));
            else chatroomService.addChatRoom(user.getUserId(),chatroom.getChatroomId());
        }catch (Exception e){
            return JsonUtils.toJSON(JsonResult.errorException("服务器错误:"+e.getMessage()));
        }
        return JsonUtils.toJSON(JsonResult.ok());

    }

    @RequestMapping(value = "/chatRoom/recommend",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String recommend(@RequestBody User user){
        List<ChatRoomVO> chatRoomVOList = chatroomService.recommendChatroom(user.getUserId());
        return JsonUtils.toJSON(JsonResult.ok(chatRoomVOList));

    }
<<<<<<< HEAD
    
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
=======

    @RequestMapping(value = "/chatRoom/create", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
>>>>>>> 149c681a20a9da1a02cce061f466913a4c397b49
    @ResponseBody
    public String createChatroom(@RequestBody Chatroom chatroom){
        Chatroom room = chatroomService.addChatroom(chatroom);
        ChatroomInfoVo chatroomInfoVo = chatroomService.getChatroomInfoById(chatroom.getChatroomId());
        return JsonUtils.toJSON(JsonResult.ok(chatroomInfoVo));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String deleteChatroom(@RequestBody Chatroom chatroom){
        boolean result = chatroomService.deleteChatroomById(chatroom.getChatroomId(), chatroom.getUserId());
        if (result){
            return JsonUtils.toJSON(JsonResult.ok());
        } else {
            return JsonUtils.toJSON(JsonResult.errorMsg("删除失败,聊天室不存在!"));
        }
    }

    @RequestMapping(value = "/getMyCreate", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String getChatroomInfoList(@RequestBody User user){
        /*List<ChatroomInfoVo> chatroomList = new ArrayList<ChatroomInfoVo>();
        try{
            chatroomList = chatroomService.getChatroomInfoList();
        }catch (Exception e){
            return JsonUtils.toJSON(JsonResult.errorException("服务器错误:"+e.getMessage()));
        }*/
        return JsonUtils.toJSON(JsonResult.ok(chatroomService.getMyCreatChatroomInfoList(user.getUserId())));
    }



    @RequestMapping(value = "/getRoomInfoById", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String getChatroomInfoById(@RequestBody Chatroom chatroom){
        ChatroomInfoVo chatroomInfoVo = new ChatroomInfoVo();
        try {
            chatroomInfoVo = chatroomService.getChatroomInfoById(chatroom.getChatroomId());
        }catch (Exception e){
            return JsonUtils.toJSON(JsonResult.errorException("服务器错误:"+e.getMessage()));
        }
        return JsonUtils.toJSON(JsonResult.ok(chatroomInfoVo));
    }

    @RequestMapping(value = "/getMyJoinRoomListById", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String getMyJoinRoomInfoById(@RequestBody User user){
        /*List<ChatroomInfoVo> chatroomList = new ArrayList<ChatroomInfoVo>();
        try{
            chatroomList = chatroomService.getChatroomInfoList();
        }catch (Exception e){
            return JsonUtils.toJSON(JsonResult.errorException("服务器错误:"+e.getMessage()));
        }*/

        return JsonUtils.toJSON(JsonResult.ok(chatroomService.getMyJoinChatroomList(user.getUserId())));

    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String updateChatroom(@RequestBody Chatroom chatroom){
        System.out.println("chatroom:"+chatroom);
        Chatroom room = chatroomService.updateChatroom(chatroom);
        ChatroomInfoVo chatroomInfoVo = chatroomService.getChatroomInfoById(chatroom.getChatroomId());
        return JsonUtils.toJSON(JsonResult.ok(chatroomInfoVo));
    }

}
