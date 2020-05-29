package com.memory.controller;


import com.alibaba.fastjson.JSONObject;
import com.memory.formbean.DriftBean;
import com.memory.pojo.Drift;
import com.memory.pojo.DriftEditor;
import com.memory.pojo.User;
import com.memory.service.DriftEditorService;
import com.memory.service.DriftService;
import com.memory.service.UserService;
import com.memory.utils.JsonResult;
import com.memory.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.Date;

@Controller
@RequestMapping("/drift")
public class DriftController {

    @Autowired
    private DriftService driftService;

    @Autowired
    private UserService userService;

    @Autowired
    private DriftEditorService driftEditorService;


    /**
     * 写漂流瓶
     * @param jsonObject 参数：
     *                          userId：用户id
     *                          content：漂流瓶内容
     * @return
     */
    @RequestMapping(value = "/write",method = RequestMethod.POST,produces = "application/json;charset = UTF-8")
    public @ResponseBody String write(@RequestBody JSONObject jsonObject){
//        System.out.println(jsonObject.getString("content"));
//        return null;
        try {
            User user = userService.get(jsonObject.getInteger("userId"));
            if(user == null){return JsonUtils.toJSON(JsonResult.errorMsg("该用户不存在！"));}
            Drift drift = new Drift();
            user.setThrowNumber(user.getThrowNumber()+1);
            drift.setUser(user);
            drift.setContent(jsonObject.getString("content"));
            drift.setEditDate(new Timestamp(new Date().getTime()));
            driftService.save(drift);
            DriftBean driftBean = DriftBean.toDriftBean(drift);
            return JsonUtils.toJSON(JsonResult.ok(driftBean));
        }catch (Exception e){
            e.printStackTrace();
            return JsonUtils.toJSON(JsonResult.errorException(e.getMessage()));
        }
    }

    /**
     * 删除指定漂流瓶
     * @param jsonObject：参数:
     *                          driftId:漂流瓶id
     *                          userId:用户id
     * @return          返回“删除成功”
     */
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE,produces = "application/json;charset = UTF-8")
    public @ResponseBody String delete(@RequestBody JSONObject jsonObject){
        try {
            User user = userService.get(jsonObject.getInteger("userId"));
            if(user == null){return JsonUtils.toJSON(JsonResult.errorMsg("该用户不存在！"));}
            Drift drift = driftService.get(jsonObject.getInteger("driftId"));
            if(drift == null){return JsonUtils.toJSON(JsonResult.errorMsg("该漂流瓶不存在！"));}
            else{
                driftEditorService.deleteAllByDriftId(jsonObject.getInteger("driftId"));
                driftService.delete(drift);
            }
            return JsonUtils.toJSON(JsonResult.ok("删除成功！"));
        }catch (Exception e){
            return JsonUtils.toJSON(JsonResult.errorException(e.getMessage()));
        }
    }


    /**
     * 查看具体某个漂流瓶
     * @param json ：driftId：漂流瓶id 即 Drift.bottleId
     * @return  无异常是返回DriftBean对象
     *           发生异常时返回异常信息
     */
    @RequestMapping(value = "view",method = RequestMethod.POST,produces = "application/json;charset = UTF-8")
    public @ResponseBody String view(@RequestBody JSONObject json){
        try {
            Drift drift = driftService.get(json.getInteger("driftId"));
            if(drift == null) return JsonUtils.toJSON(JsonResult.errorMsg("该漂流瓶不存在id："
                    +json.getInteger("driftId")));
            DriftBean driftBean = DriftBean.toDriftBean(drift);
            driftBean.setComments(driftEditorService.getAllByDriftId(json.getInteger("driftId")));
            return JsonUtils.toJSON(JsonResult.ok(driftBean));
        }catch (Exception e){
            return JsonUtils.toJSON(JsonResult.errorException(e.getMessage()));
        }
    }


    /**
     * 回复漂流瓶
     * @param json:replayId：回复者id
     *               message：回复内容
     *               driftId：漂流瓶id
     * @return 是否抛出成功
     */
    @RequestMapping(value = "/send",method = RequestMethod.POST,produces = "application/json;charset = UTF-8")
    public @ResponseBody String send(@RequestBody JSONObject json){
        System.out.println(json);
        try {
            Drift drift = driftService.get(json.getInteger("driftId"));
            if(drift == null) return JsonUtils.toJSON(JsonResult.errorMsg("该漂流瓶不存在，id："
                    +json.getInteger("driftId")));
            DriftEditor driftEditor = new DriftEditor();
            driftEditor.setComment(json.getString("message"));
            driftEditor.setEditDate(new Timestamp(new Date().getTime()));
            driftEditor.setUserId(json.getInteger("replayId"));
            driftEditor.setDrift(drift);
            driftEditorService.save(driftEditor);
            drift.setFlag((byte)0);
            driftService.update(drift);
            DriftBean driftBean = DriftBean.toDriftBean(drift);
            driftBean.setComments(driftEditorService.getAllByDriftId(json.getInteger("driftId")));
            return JsonUtils.toJSON(JsonResult.ok(driftBean));
        }catch (Exception e){
            return JsonUtils.toJSON(JsonResult.errorException(e.getMessage()));
        }
    }
}
