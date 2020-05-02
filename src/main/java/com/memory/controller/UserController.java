package com.memory.controller;


import com.alibaba.fastjson.JSONObject;
import com.memory.pojo.Tag;
import com.memory.pojo.User;
import com.memory.pojo.UserTag;
import com.memory.service.MsgService;
import com.memory.service.TagService;
import com.memory.service.UserService;
import com.memory.service.UserTagService;
import com.memory.utils.JsonResult;
import com.memory.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserTagService userTagService;

    @Autowired
    private TagService tagService;

    @Autowired
    private MsgService msgService;
    /**
     * 修改个人信息
     * @param
     * @return
     */
    @RequestMapping(value = "/modifyInformation", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public @ResponseBody String modifyInformation (@RequestBody User user){
        User u;
        System.out.println(user);
        try {
            u = userService.get(user.getUserId());
        }catch (Exception e){
            return JsonUtils.toJSON(JsonResult.errorException("查询错误"));
        }
        if( u ==null ){                         //can not find the user
            return JsonUtils.toJSON(JsonResult.errorMsg("找不到该用户：uid= "+user.getUserId()));
        }
        u.setNickname(user.getNickname());
        if (user.getIcon() != null || user.getIcon().length()!=0 )
            u.setIcon(user.getIcon());
        u.setGender(user.getGender());
        if (user.getProfile() != null || user.getProfile().length() != 0 )
            u.setProfile(user.getProfile());
        try {
            userService.update(u);
        }catch (Exception e){
            return JsonUtils.toJSON(JsonResult.errorException("修改错误"));
        }
        return JsonUtils.toJSON(JsonResult.ok(u));
    }

    /**
     * 获取过去标签
     * @param
     * @return
     */
    @RequestMapping(value = "/pastTag" , method = RequestMethod.GET)
    public @ResponseBody String pastTag(int userId){
        //System.out.println("userId"+userId);
        List<String> tagList = new LinkedList<>();
        List<UserTag> list = null;
        try {
            list = userTagService.getByUserId(userId);
        }catch (Exception e){
            return JsonUtils.toJSON(JsonResult.build(1000,"args error",""));
        }
        for(UserTag ut:list){
            Tag t = tagService.get(ut.getTagId());
            tagList.add(t.getTagName());
        }
        return JsonUtils.toJSON(JsonResult.ok(tagList));
    }

    /**
     * 设置本周标签
     * @param
     * @return
     */
    @RequestMapping(value = "/setThisWeekTag" , method = RequestMethod.POST)
    public @ResponseBody String setThisWeekTag(@RequestBody User user){
        User u;
        try {
            u = userService.get(user.getUserId());               //查找User
        }catch (Exception e){
            return JsonUtils.toJSON(JsonResult.errorException("查询错误"));
        }
        if( u == null)
            return JsonUtils.toJSON(JsonResult.errorMsg("该用户不存在"));
        String lastTag = u.getThisWeekTag();
        String[] lastTagList = lastTag.split(",");
        for(String s: lastTagList){                     //update UserTag chart
            Tag t = tagService.getByTagName(s);
            UserTag ut = userTagService.get(user.getUserId(),t.getTagId());
            if (ut == null){
                ut = new UserTag();
                ut.setUserId(user.getUserId());
                ut.setTagId(t.getTagId());
                ut.setTagNumber(1);
                userTagService.save(ut);
            }else {
                ut.setTagNumber(ut.getTagNumber() + 1);
                userTagService.update(ut);
            }
        }
        try {
            u.setThisWeekTag(user.getThisWeekTag());            //update user.thisWeekTag
            userService.update(u);
        }catch (Exception e){
            return JsonUtils.toJSON(JsonResult.errorException("服务器错误"));
        }
        return JsonUtils.toJSON(JsonResult.ok());
    }

    /**
     * 获取本周标签
     * @param
     * @return
     */
    @RequestMapping(value = "/getThisTags",method = RequestMethod.POST)
    public @ResponseBody String getThisTags(@RequestBody User user){
        User u;
        try {
            u = userService.get(user.getUserId());
        }catch (Exception e){
            return JsonUtils.toJSON(JsonResult.errorException("查询错误"));
        }
        String tags = u.getThisWeekTag();
        String[] list = null;
        if(tags != null) {
            list = tags.split(",");
        }
        return JsonUtils.toJSON(JsonResult.ok(list));
    }

    /**
     * 获取用户信息
     * @param user
     * @return
     */
    @RequestMapping(value = "/getUser", method = RequestMethod.POST)
    public @ResponseBody String getUser(@RequestBody User user){
        User u = userService.get(user.getUserId());
        return JsonUtils.toJSON(JsonResult.ok(u));
    }

    @RequestMapping(value = "/getUnReadMsgList",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String getUnReadMsgList(Integer acceptUserId) {
        if (acceptUserId==null) {
            return JsonUtils.toJSON(JsonResult.errorMsg("用户民不能为空"));
        }
        List<com.memory.netty.Msg> msgList = msgService.getUnReadMsgList(acceptUserId);
        return JsonUtils.toJSON(JsonResult.ok(msgList));
    }
}
