package com.memory.controller;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.memory.pojo.User;
import com.memory.service.UserServiceImpl;
import com.memory.utils.AliyunSmsUtils;
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
 * @date 2020/5/1 - 8:41
 */

@Controller
public class GetVerificationCode {
    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected UserServiceImpl service;


    @ResponseBody
    @RequestMapping(value = "/user/getCode",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String login2 (@RequestBody User iuser){
        //传进来的参数仅有telephone
        String telephone = iuser.getTelephone();

        AliyunSmsUtils.setNewcode();

        String code = Integer.toString(AliyunSmsUtils.getNewcode());

        System.out.println("发送的验证码为："+code);

        //发短信

        try{
            SendSmsResponse response =AliyunSmsUtils.sendSms(telephone,code);

            //窗口打印一下发送的验证码；
            System.out.println("短信接口返回的数据----------------");

            System.out.println("Code=" + response.getCode());

            System.out.println("Message=" + response.getMessage());

            System.out.println("RequestId=" + response.getRequestId());

            System.out.println("BizId=" + response.getBizId());


        }catch (ClientException exception){
            System.out.println("服务器发送短信异常，大概为客户端异常！！");
            return JsonUtils.toJSON(JsonResult.errorMsg("发送短信验证码失败，请查看手机短信功能状态或联系产品售后人员"));
        }

        //没有任何异常，给客户端返回验证码用于后台校验
        return JsonUtils.toJSON(JsonResult.ok(code));
    }
}
