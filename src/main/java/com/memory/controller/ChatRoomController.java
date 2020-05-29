package com.memory.controller;


import com.memory.controller.VO.ChatRoomVO;
import com.memory.service.ChatroomService;
import com.memory.utils.JsonResult;
import com.memory.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/chatRoom")
public class ChatRoomController {

    @Autowired
    ChatroomService chatroomService;


    @RequestMapping(value = "/searchById",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String searchById(Integer chatroomId){
            return chatroomService.searchById(chatroomId);
    }

    @RequestMapping(value = "/searchByTag",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String searchByTag(String chatroomTag){
            return chatroomService.searchByTag(chatroomTag);
    }

    @RequestMapping(value = "/searchByName",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String searchByName(String chatroomName){
            return chatroomService.searchByName(chatroomName);
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String add(Integer userId,Integer chatRoomId){
                try{
                    if(!chatroomService.isExistChatRoom(chatRoomId))  return JsonUtils.toJSON(JsonResult.errorMsg("不存在此聊天室"));
                        else if(chatroomService.isInChatRoom(userId,chatRoomId)) return JsonUtils.toJSON(JsonResult.errorMsg("你已经在聊天室内"));
                            else chatroomService.addChatRoom(userId,chatRoomId);
                }catch (Exception e){
                    return JsonUtils.toJSON(JsonResult.errorException("服务器错误:"+e.getMessage()));
                }
                  return JsonUtils.toJSON(JsonResult.ok());

    }

    @RequestMapping(value = "/recommend",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String recommend(Integer userId){
        List<ChatRoomVO> chatRoomVOList = chatroomService.recommendChatroom(userId);
        return JsonUtils.toJSON(JsonResult.ok(chatRoomVOList));

    }
}
