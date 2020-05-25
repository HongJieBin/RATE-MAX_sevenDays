package com.memory.controller.corner;

import com.memory.service.CornerServiceImpl;
import com.memory.utils.JsonResult;
import com.memory.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author hy
 * @date 2020/5/3 - 10:06
 * 评论
 */

@Controller
public class Comment {
    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected CornerServiceImpl service;

    @RequestMapping(value = "/corner/comment",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String comment(@RequestBody com.memory.pojo.Comment comment) {
        //send_id,post_id,post_content必选参数,post_emoji可选参数
        //属性sendUser{userId},post{postId},postContent,,,postEmoji
        try{
            service.postComment(comment);
            return JsonUtils.toJSON(JsonResult.ok("评论成功"));
        }catch (Exception e){
            e.printStackTrace(); // 在后台记录异常
            return JsonUtils.toJSON(JsonResult.errorMsg("后台记录异常，评论失败"));
        }
    }
}
