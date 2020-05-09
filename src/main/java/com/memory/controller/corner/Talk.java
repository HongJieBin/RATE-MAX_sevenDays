package com.memory.controller.corner;

import com.memory.pojo.Post;
import com.memory.pojo.User;
import com.memory.service.CornerServiceImpl;
import com.memory.service.UserServiceImpl;
import com.memory.utils.JsonResult;
import com.memory.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

/**
 * @author hy
 * @date 2020/5/3 - 10:06
 * 发布一条动态
 */

@Controller
public class Talk {

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected CornerServiceImpl service;

    @RequestMapping(value = "/corner/talk",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String talk(@RequestBody Post post) {
        //post_image,post_emoji可选参数，post_content,send_id，必选参数
        //属性postImage,postEmoji,,,   postContent,user{userId}...
        post.setPostDate(new Timestamp(System.currentTimeMillis()));
        post.setPostLike(0);
        try{
            service.postTalk(post);
            return JsonUtils.toJSON(JsonResult.ok("发布动态成功"));
        }catch (Exception e){
            e.printStackTrace(); // 在后台记录异常
            return JsonUtils.toJSON(JsonResult.errorMsg("后台记录异常，发布动态失败"));
        }
    }
}
