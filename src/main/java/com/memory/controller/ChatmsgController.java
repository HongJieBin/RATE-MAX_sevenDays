package com.memory.controller;

import com.memory.controller.VO.ChatMsgRequestVO;
import com.memory.controller.VO.ChatRoomMsgVO;
import com.memory.pojo.Msg;
import com.memory.service.ChatMsgService;
import com.memory.service.FriendService;
import com.memory.utils.JsonResult;
import com.memory.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/chatRoomMsg")
public class ChatmsgController {

    @Autowired
    private FriendService friendService;
    @Autowired
    private ChatMsgService chatMsgService;

    @PostMapping(value = "/unreadMsgs", produces = {"application/json;charset=UTF-8"})
    public @ResponseBody
    String getUnReadChatMsgList(@RequestBody ChatMsgRequestVO chatMsgRequestVO) {

        int acceptUserId = chatMsgRequestVO.getAcceptUserId();
        if (!friendService.isExitUser(acceptUserId)) {
            return JsonUtils.toJSON(JsonResult.errorMsg("不存在此用户"));
        }
        else {
            List<ChatRoomMsgVO> chatRoomMsgVOList =chatMsgService.getUnReadChatMsgList(acceptUserId);
            return JsonUtils.toJSON(JsonResult.ok(chatRoomMsgVOList));
        }
    }

    @PostMapping(value = "/AllMsgs", produces = {"application/json;charset=UTF-8"})
    public @ResponseBody
    String getAllChatMsgList(@RequestBody ChatMsgRequestVO chatMsgRequestVO) {

        int acceptUserId = chatMsgRequestVO.getAcceptUserId();
        int chatroomId = chatMsgRequestVO.getChatroomId();
        if (!friendService.isExitUser(acceptUserId)) {
            return JsonUtils.toJSON(JsonResult.errorMsg("不存在此用户"));
        }
        else {
            List<ChatRoomMsgVO> chatRoomMsgVOList = chatMsgService.getAllChatMsgList(acceptUserId,chatroomId);
            return JsonUtils.toJSON(JsonResult.ok(chatRoomMsgVOList));
        }
    }




}
