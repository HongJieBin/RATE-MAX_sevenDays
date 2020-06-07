package com.memory.controller;


import com.memory.controller.VO.ChatRoomVO;
import com.memory.controller.VO.ChatroomInfoVo;
import com.memory.controller.VO.addVO;
import com.memory.dao.ChatroomDAO;
import com.memory.pojo.Chatroom;
import com.memory.pojo.User;
import com.memory.service.ChatroomService;
import com.memory.utils.JsonResult;
import com.memory.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/chatRoom")
public class ChatroomController {

    @Autowired
    ChatroomService chatroomService;
    @Autowired
    ChatroomDAO chatroomDAO;


    @RequestMapping(value = "/searchById",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String searchById(@RequestBody Chatroom chatroom){
        return chatroomService.searchById(chatroom.getChatroomId());
    }

    @RequestMapping(value = "/searchByTag",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String searchByTag(@RequestBody Chatroom chatroom){
        return chatroomService.searchByTag(chatroom.getChatroomTag());
    }

    @RequestMapping(value = "/searchByName",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String searchByName(@RequestBody Chatroom chatroom){
        return chatroomService.searchByName(chatroom.getChatroomName());
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String add(@RequestBody addVO addVO){
        try{
            if(!chatroomService.isExistChatRoom(addVO.getChatroomId()))  return JsonUtils.toJSON(JsonResult.errorMsg("不存在此聊天室"));
            else if(chatroomService.isInChatRoom(addVO.getUserId(),addVO.getChatroomId())) return JsonUtils.toJSON(JsonResult.errorMsg("你已经在聊天室内"));
            else if(chatroomDAO.get(addVO.getChatroomId()).getChatroomStatement() == 1) return  JsonUtils.toJSON(JsonResult.errorMsg("该聊天室已经关闭"));
            else chatroomService.addChatRoom(addVO.getUserId(),addVO.getChatroomId());
        }catch (Exception e){
            return JsonUtils.toJSON(JsonResult.errorException("服务器错误:"+e.getMessage()));
        }
        return JsonUtils.toJSON(JsonResult.ok());

    }

    @RequestMapping(value = "/recommend",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String recommend(@RequestBody User user){
        List<ChatRoomVO> chatRoomVOList = chatroomService.recommendChatroom(user.getUserId());
        return JsonUtils.toJSON(JsonResult.ok(chatRoomVOList));

    }
    
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String createChatroom(@RequestBody Chatroom chatroom){
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
        ChatroomInfoVo chatroomInfoVo;
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
        List<ChatroomInfoVo> chatroomList;
        try{
            chatroomList = chatroomService.getMyJoinChatroomList(user.getUserId());
        }catch (Exception e){
            return JsonUtils.toJSON(JsonResult.errorException("服务器错误:"+e.getMessage()));
        }
        return JsonUtils.toJSON(JsonResult.ok(chatroomList));
        //return JsonUtils.toJSON(JsonResult.ok(chatroomService.getMyJoinChatroomList(user.getUserId())));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String updateChatroom(@RequestBody Chatroom chatroom){
        System.out.println("chatroom:"+chatroom);
        ChatroomInfoVo chatroomInfoVo = chatroomService.getChatroomInfoById(chatroom.getChatroomId());
        return JsonUtils.toJSON(JsonResult.ok(chatroomInfoVo));
    }

    @RequestMapping(value = "/getBeforeRoomListById", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String getBeforeRoomInfoById(@RequestBody User user){
        List<ChatroomInfoVo> chatroomList;
        try{
            chatroomList = chatroomService.getBeforeChatroomList(user.getUserId());
        }catch (Exception e){
            return JsonUtils.toJSON(JsonResult.errorException("服务器错误:"+e.getMessage()));
        }
        return JsonUtils.toJSON(JsonResult.ok(chatroomList));
        //return JsonUtils.toJSON(JsonResult.ok(chatroomService.getBeforeChatroomList(user.getUserId())));
    }
}
