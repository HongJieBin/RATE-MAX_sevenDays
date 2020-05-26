package com.memory.controller.corner;

import com.memory.dao.CommentDAO;
import com.memory.pojo.Comment;
import com.memory.pojo.Post;
import com.memory.service.CornerService;
import com.memory.utils.JsonResult;
import com.memory.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.image.TileObserver;
import java.util.List;

/**
 * @author hy
 * @date 2020/5/7 - 14:38
 */

@Controller
public class GetComment {

    @Autowired
    private CornerService cornerService;

    @RequestMapping(value = "/corner/getcomment",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String getLatestComment(int start, int max, int postId){
        String postid=Integer.toString(postId);
        List<Comment> comments=null;
        System.out.println(postid);
        try{
            comments = cornerService.getLatestComment(start,max,postid);
        }catch (Exception e){
            System.out.println("评论列表崩了");
        }

        return JsonUtils.toJSON(JsonResult.ok(comments));
    }

}
