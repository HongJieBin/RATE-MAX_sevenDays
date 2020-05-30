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

import java.util.List;

@Controller
@RequestMapping(value = "/chatRoom")
public class ChatroomController {

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
    
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
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
        return JsonUtils.toJSON(JsonResult.ok(chatroomService.getMyJoinChatrommList(user.getUserId())));
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
