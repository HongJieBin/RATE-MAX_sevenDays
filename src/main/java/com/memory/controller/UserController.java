package com.memory.controller;


import com.alibaba.fastjson.JSONObject;
import com.memory.pojo.Tag;
import com.memory.pojo.User;
import com.memory.pojo.UserTag;
import com.memory.service.TagServiceImpl;
import com.memory.service.UserService;
import com.memory.service.UserTagServiceImpl;
import com.memory.utils.JsonResult;
import com.memory.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    private UserTagServiceImpl userTagService;

    @Autowired
    private TagServiceImpl tagService;

    /**
     * 修改个人信息
     * @param
     * @return
     */
    @RequestMapping(value = "/modifyInformation", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public @ResponseBody String modifyInformation (@RequestBody JSONObject json){
        User u;
        //System.out.println(user);
        try {
            u = userService.get(json.getInteger("userId"));
        }catch (Exception e){
            return JsonUtils.toJSON(JsonResult.errorException("查询错误"));
        }
        if( u == null ){                         //can not find the user
            return JsonUtils.toJSON(JsonResult.errorMsg("找不到该用户：uid= "+json.getInteger("userId")));
        }
        u.setNickname(json.getString("nickname"));
        if (json.getString("icon") != null && json.getString("icon").length()!=0 )
            u.setIcon(json.getString("icon"));
        u.setGender(json.getString("gender"));
        if (json.getString("profile") != null )
            u.setProfile(json.getString("profile"));
        try {
            userService.update(u);
        }catch (Exception e){
            return JsonUtils.toJSON(JsonResult.errorException("保存失败！\nMessage:"+e.getMessage()));
        }
        System.out.println(JsonResult.ok(u));
        return JsonUtils.toJSON(JsonResult.ok(u));
    }

    /**
     * 获取过去标签
     * @param
     * @return
     */
    @RequestMapping(value = "/pastTag" , method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public @ResponseBody String pastTag(@RequestBody JSONObject json){
        List<String> tagList = new LinkedList<>();
        List<UserTag> list = null;
        try {
            list = userTagService.getByUserId(json.getInteger("userId"));
        }catch (Exception e){
            System.out.println(e.getCause()+":"+e.getMessage());
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
    @RequestMapping(value = "/setThisWeekTag" , method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public @ResponseBody String setThisWeekTag(@RequestBody User user){
        User u;
        try {
            u = userService.get(user.getUserId());               //查找User
        }catch (Exception e){
            return JsonUtils.toJSON(JsonResult.errorException("查询错误"));
        }
        if( u == null)
            return JsonUtils.toJSON(JsonResult.errorMsg("该用户不存在"));
        String lastTag = u.getThisWeekTag();                    //获取上周标签
        String[] lastTagList = null;
        if( lastTag != null) {
            lastTagList = lastTag.split(" ");//将字符串分开
        System.out.println(lastTagList);

            for (String s : lastTagList) {//update UserTag chart
                System.out.println(s);
                System.out.println(111);
                if(s!=null) {
                    System.out.println(111);
                    Tag t = tagService.getByTagName(s);
                    UserTag ut = userTagService.get(user.getUserId(), t.getTagId());
                    if (ut == null) {
                        ut = new UserTag();
                        ut.setUserId(user.getUserId());
                        ut.setTagId(t.getTagId());
                        ut.setTagNumber(1);
                        userTagService.save(ut);
                    } else {
                        ut.setTagNumber(ut.getTagNumber() + 1);
                        userTagService.update(ut);
                    }
                }
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
    @RequestMapping(value = "/getThisTags",method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
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
            list = tags.split(" ");
        }
        return JsonUtils.toJSON(JsonResult.ok(list));
    }

    /**
     * 获取用户信息，以后可能会用到
     * @param user
     * @return
     */
    @RequestMapping(value = "/getUser", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public @ResponseBody String getUser(@RequestBody User user){
        User u = userService.get(user.getUserId());
        return JsonUtils.toJSON(JsonResult.ok(u));
    }
}
