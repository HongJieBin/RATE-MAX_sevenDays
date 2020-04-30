package com.memory.controller;

import com.memory.pojo.Msg;
import com.memory.service.ChatMsgService;
import com.memory.service.FriendService;
import com.memory.utils.JsonResult;
import com.memory.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class MsgController {
    @Autowired
    private ChatMsgService chatMsgService;

    @Autowired
    private FriendService friendService;

    @PostMapping("/unreadMsgs")
    public @ResponseBody String getUnReadMsgList(int acceptUserId) {
        //if(!friendService.isExitUser(acceptUserId)){ }
        if (!friendService.isExitUser(acceptUserId)) {
            return JsonUtils.toJSON(JsonResult.errorMsg("不存在此用户"));
        }
        else {
            List<Msg> unreadMsgList = chatMsgService.getUnReadMsgList(acceptUserId);
            return JsonUtils.toJSON(JsonResult.ok(unreadMsgList));
        }
    }

    @PostMapping("/allMsgs")
    public @ResponseBody String getAllMsgList(int userId, int receiveId) {
        List<Msg> unreadMsgList = chatMsgService.getAllMsgList(userId,receiveId);
        return JsonUtils.toJSON(JsonResult.ok(unreadMsgList));
    }




}
