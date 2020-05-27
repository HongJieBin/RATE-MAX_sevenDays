package com.memory.controller;


import com.memory.pojo.Qiniu;
import com.memory.utils.JsonResult;
import com.memory.utils.JsonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

import java.util.UUID;

@Controller
@RequestMapping(value = "upload")
public class ImageUploadController {

    private String AK = "PtpL4YaLivm6f0puAXE7iQdummwzGr-aERMNInxJ";
    private String SK = "KuMLbnjRtxO32NY2Wkk3ZlKiHD9CWkjN3yLmmAc1";
    private String Bucket = "seven-days-memory";

    @RequestMapping(value = "/getToken",method = RequestMethod.GET)
    public @ResponseBody String getToken(){
        long expireSeconds = 600; //过期时间
        Auth auth = Auth.create(AK,SK);
        StringMap putPolicy = new StringMap();
        Qiniu qiniu = new Qiniu();
        qiniu.setToken(auth.uploadToken(Bucket,null,expireSeconds,putPolicy));
        qiniu.setKey(UUID.randomUUID().toString().replace("\\-",""));
        return JsonUtils.toJSON(JsonResult.ok(qiniu));
    }
}
