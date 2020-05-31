package com.memory.controller.drift;

import com.memory.pojo.Drift;
import com.memory.service.CornerServiceImpl;
import com.memory.service.DriftService;
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

/**
 * @author hy
 * @date 2020/5/26 - 9:42
 */
@Controller
public class ViewController {
    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected DriftServiceImpl service;

    @RequestMapping(value = "/drift/view",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String view(@RequestBody Drift drift) {
        //drift.bottleId必选
        Drift drift1=null;
        try{
            drift1=service.view(drift.getBottleId());
            return JsonUtils.toJSON(JsonResult.ok(drift1));
        }catch (Exception e){
            e.printStackTrace(); // 在后台记录异常
            return JsonUtils.toJSON(JsonResult.errorMsg("后台记录异常，获取drift内容失败"));
        }
    }
}
