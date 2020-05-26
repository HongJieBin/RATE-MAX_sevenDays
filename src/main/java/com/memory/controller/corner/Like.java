package com.memory.controller.corner;

import com.memory.pojo.Post;
import com.memory.pojo.PostLike;
import com.memory.service.CornerService;
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
 * @date 2020/5/3 - 10:07
 * 点赞
 */

@Controller
public class Like {
    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected CornerServiceImpl service;

    @RequestMapping(value = "/corner/like",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String like(@RequestBody PostLike postLike){
        try{
            service.postLike(postLike);
            return JsonUtils.toJSON(JsonResult.ok("点赞成功"));
        }catch (Exception e){
            e.printStackTrace(); // 在后台记录异常
            return JsonUtils.toJSON(JsonResult.errorMsg("后台记录异常，点赞失败"));
        }
    }

    @RequestMapping(value = "/corner/notlike",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String notlike(@RequestBody PostLike postLike){
        try{
            service.notpostLike(postLike);
            return JsonUtils.toJSON(JsonResult.ok("取消点赞成功"));
        }catch (Exception e){
            e.printStackTrace(); // 在后台记录异常
            return JsonUtils.toJSON(JsonResult.errorMsg("后台记录异常，点赞失败"));
        }
    }

    @RequestMapping(value = "/corner/likeornot",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String likeornot(@RequestBody PostLike postLike){
        PostLike t = null;
        try{
            t = service.likeOrNot(postLike);
            if(t!=null){
                return JsonUtils.toJSON(JsonResult.ok("已经点赞"));
            }else{
                return JsonUtils.toJSON(JsonResult.ok("尚未点赞"));
            }
        }catch (Exception e){
            e.printStackTrace(); // 在后台记录异常
            return JsonUtils.toJSON(JsonResult.errorMsg("后台记录异常，点赞失败"));
        }
    }
}
