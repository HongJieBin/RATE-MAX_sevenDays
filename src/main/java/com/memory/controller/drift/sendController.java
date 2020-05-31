package com.memory.controller.drift;
import com.memory.pojo.DriftEditor;
import com.memory.service.DriftServiceImpl;
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
 * @date 2020/5/26 - 10:35
 */
@Controller
public class sendController {
    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected DriftServiceImpl service;

    @RequestMapping(value = "/drift/send",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String send(@RequestBody DriftEditor driftEditor) {
        //editId,comment,userId,drift{bottleId}必选
        Timestamp d = new Timestamp(System.currentTimeMillis());
        driftEditor.setEditDate(d);
        System.out.println(driftEditor);
        try{
            service.send(driftEditor);
            return JsonUtils.toJSON(JsonResult.ok("回复漂流瓶成功"));
        }catch (Exception e){
            e.printStackTrace(); // 在后台记录异常
            return JsonUtils.toJSON(JsonResult.errorMsg("后台记录异常，回复漂流瓶失败"));
        }
    }
}
