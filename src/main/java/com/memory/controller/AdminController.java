package com.memory.controller;

import com.alibaba.fastjson.JSONObject;
import com.memory.pojo.Admin;
import com.memory.pojo.Report;
import com.memory.pojo.Tag;
import com.memory.service.*;
import com.memory.utils.JsonResult;
import com.memory.utils.JsonUtils;
import org.hibernate.jpa.event.internal.core.JpaSaveOrUpdateEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReportService reportService;

    @Autowired
    private TagService tagService;

    @Autowired
    private ChatroomService chatroomService;

    @RequestMapping(value = "logIn",method = RequestMethod.POST)
    public String logIn(@RequestParam(name = "adminName")String name,@RequestParam(name = "adminPassword")String password, HttpSession session, HttpServletRequest request){
        Admin admin1 = null;
        Admin admin = new Admin();
        admin.setAdminName(name);
        admin.setAdminPassword(password);
        try {
            admin1 = adminService.logIn(admin);
            if(admin1 != null){
                if(admin1.getAdminPassword().equals(admin.getAdminPassword())){
                    session.setAttribute("adminId",admin1.getAdminId());
                    session.setAttribute("adminName",admin1.getAdminName());
                    request.setAttribute("code",200);
                    request.setAttribute("msg","登录成功！");
                    request.setAttribute("users",userService.getAll());
                    return "listUser";
                }else {
                    System.out.println("密码错误");
                    request.setAttribute("code",2008);
                    request.setAttribute("msg","密码错误");
                    request.setAttribute("users",null);
                    return "adminLogin";
                }
            }
        }catch (Exception e){
            System.out.println("发生异常");
            e.printStackTrace();
            System.out.println("error");
            request.setAttribute("code",1999);
            request.setAttribute("msg","服务器错误");
            request.setAttribute("users",null);
            return "adminLogin";
        }
        System.out.println("管理员不存在");
        request.setAttribute("code",2008);
        request.setAttribute("msg","该管理员不存在");
        request.setAttribute("users",null);
        return "adminLogin";
    }

    /**
     * 按举报类型和举报原因类型来查看举报
     * @param json  reportTypeId:举报类型id（不指定类型是为0）
     *               reportReasonId:举报原因类型id（不指定原因时为0）
     * @return
     */
    @RequestMapping(value = "viewReport",method = RequestMethod.POST,produces = "applictaion/json;charset = UTF-8")
    public @ResponseBody String viewReport(@RequestBody JSONObject json){
        int reportReasonId = json.getInteger("reportReasonId");
        int reportType = json.getInteger("reportTypeId");
        List<Report> list;
        try {
           list = reportService.getReport(reportType,reportReasonId);
        }catch (Exception e){
            return JsonUtils.toJSON(JsonResult.errorException(e.getMessage()));
        }
        return JsonUtils.toJSON(JsonResult.ok(list));
    }

    /**
     * 添加标签
     * @param json：tags：标签组成的字符串，用‘，’分割
     * @return
     */
    @RequestMapping(value = "addTags",method = RequestMethod.POST,produces = "application/json;charset = UTF-8")
    public @ResponseBody String addTags(@RequestBody JSONObject json){
        String tags = json.getString("tags");
        String[] list = tags.split(",");
        for(String s : list){
            Tag tag = new Tag();
            tag.setTagName(s);
            tagService.add(tag);
        }
        return JsonUtils.toJSON(JsonResult.ok("添加成功！"));
    }


    /**
     * 获取所有聊天室
     * @param json
     * @return
     */
    @RequestMapping(value = "getAllChatRoom",method = RequestMethod.GET,produces = "application/json;charset = UTF-8")
    public @ResponseBody String getAllChatRoom(@RequestBody JSONObject json){
        try {
            return JsonUtils.toJSON(JsonResult.ok(chatroomService.getAll()));
        }catch (Exception e){
            return JsonUtils.toJSON(JsonResult.errorException(e.getMessage()));
        }
    }

    /**
     * 获取指定聊天室
     * @param json:chatRoomId
     * @return
     */
    @RequestMapping(value = "getChatRoom",method = RequestMethod.POST,produces = "application/json;charset = UTF-8")
    @ResponseBody
    public String getChatRoom(@RequestBody JSONObject json){
        int chatRoomId = json.getInteger("chatRoomId");
        try {
            return JsonUtils.toJSON(JsonResult.ok(chatroomService.get(chatRoomId)));
        }catch (Exception e){
            return JsonUtils.toJSON(JsonResult.errorException(e.getMessage()));
        }
    }

    /**
     * 打开聊天室
     * @param json
     * @return
     */
    @RequestMapping(value = "openChatRoom",method = RequestMethod.POST,produces = "application/json;charset = UTF-8")
    @ResponseBody
    public String openChatRoom(@RequestBody JSONObject json){
        int chatRoomId = json.getInteger("chatRoomId");
        try {
            chatroomService.openChatRoom(chatRoomId);
        }catch (Exception e){
            return JsonUtils.toJSON(JsonResult.errorException(e.getMessage()));
        }
        return JsonUtils.toJSON(JsonResult.ok("打开聊天室成功！"));
    }

    /**
     * 关闭聊天室
     * @param json
     * @return
     */
    @RequestMapping(value = "closeChatRoom",method = RequestMethod.POST,produces = "application/json;charset = UTF-8")
    @ResponseBody
    public String closeChatRoom(@RequestBody JSONObject json){
        int chatRoomId = json.getInteger("chatRoomId");
        try {
            chatroomService.closeChatRoom(chatRoomId);
        }catch (Exception e){
            return JsonUtils.toJSON(JsonResult.errorException(e.getMessage()));
        }
        return JsonUtils.toJSON(JsonResult.ok("关闭聊天室成功！"));
    }

    @RequestMapping(value = "handleReport",method = RequestMethod.POST,produces = "application/json;charset = UFT-8")
    @ResponseBody
    public String handleReport(@RequestBody JSONObject json){
        return null;
    }
}