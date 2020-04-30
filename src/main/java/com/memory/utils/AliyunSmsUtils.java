package com.memory.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/**
 * @author hy
 * @date 2020/4/24 - 23:14
 *
 * 短信验证的实现工具类，基本不做变动
 *
 * 阿里云服务费一条信息0.045元
 *
 *
 *
 * 测试阶段的手机短信验证码标准：预警手机号       13386918613     暂用hy的手机号
 *
 *
 * 简单的后台设置：
 *
 * 同一个签名，对同一个手机号的发送频率
 * 1分钟内短信发送条数不超过：
 * 1
 *
 * 1小时内短信发送条数不超过：
 * 5
 *
 * 1个自然日内短信发送条数不超过：
 * 10
 *
 * 开启防盗刷监控
 * 每小时（整点整分）的验证码发送量超过 50 条时，开始监控。
 *
 *
 * 触发防盗刷预警
 * 发送量达到可监控指标时，发送成功率低于 80% 且发送增长率高于 100% 时，触发预警。
 *
 * 发送成功率：每个小时（整点整分），验证码短信发送成功量/验证码短信发送总量。
 * 发送增长率：[当天每个小时（整点整分）短信发送量-前一天同时段短信发送量]/前一天同时段短信发送量。
 *
 * 验证码攻击：利用用户产品验证码获取的接口，通过程序的方式批量对单个或者多个号码进⾏验证码重复请求提交，
 * 用户验证码被刷后直接带来的是经济损失，同时对被攻击的号码带来了巨大的骚扰。
 *
 * 每日发送总量达 100 条时预警，达 150 条时暂停本日短信发送
 * 每月发送总量达 1000 条时预警，达 1500 条时暂停本月短信发送
 *
 *
 */



public class AliyunSmsUtils {



    //产品名称:云通信短信API产品

    static final String product = "Dysmsapi";

    //产品域名

    static final String domain = "dysmsapi.aliyuncs.com";


    static final String accessKeyId = "LTAI4G1XqnF52E7jbM2D3uvr";        // TODO 修改成自己的accessKeyId

    static final String accessKeySecret = "i4aASYcxap8HN4CYZ3xO4mgTcd42Zs";     // TODO 修改成自己的accessKeySecret



    public static SendSmsResponse sendSms(String telephone, String code) throws ClientException {

        //可自助调整超时时间

        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");

        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化

        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);

        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);

        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容

        SendSmsRequest request = new SendSmsRequest();

        //必填:待发送手机号

        request.setPhoneNumbers(telephone);

        //必填:短信签名-可在短信控制台中找到

        request.setSignName("SevenDay");

        //必填:短信模板-可在短信控制台中找到

        request.setTemplateCode("SMS_189030844");

        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为

//        request.setTemplateParam("{\"name\":\"Tom\", \"code\":\"123\"}");

        request.setTemplateParam("{\"code\":\"" + code + "\"}");

        //选填-上行短信扩展码(本产品无此需求，忽略此字段)

        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者

        request.setOutId("yourOutId");

        //hint 此处可能会抛出异常，catch一下

        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

        if(sendSmsResponse.getCode()!= null && sendSmsResponse.getCode().equals("OK")){

            System.out.println("短信发送成功！");

        }else {

            System.out.println("短信发送失败！");

        }

        return sendSmsResponse;

    }





  /*    群发扩展，暂时不考虑群发

  public static QuerySendDetailsResponse querySendDetails(String bizId) throws ClientException {

        //可自助调整超时时间

        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");

        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化

        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);

        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);

        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象

        QuerySendDetailsRequest request = new QuerySendDetailsRequest();

        //必填-号码

        request.setPhoneNumber("15000000000");

        //可选-流水号

        request.setBizId(bizId);

        //必填-发送日期 支持30天内记录查询，格式yyyyMMdd

        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");

        request.setSendDate(ft.format(new Date()));

        //必填-页大小

        request.setPageSize(10L);

        //必填-当前页码从1开始计数

        request.setCurrentPage(1L);

        //hint 此处可能会抛出异常，catch一下

        QuerySendDetailsResponse querySendDetailsResponse = acsClient.getAcsResponse(request);

        return querySendDetailsResponse;

    }

*/



    //以下为测试代码，随机生成验证码

    private static int newcode;

    public static int getNewcode() {

        return newcode;

    }

    public static void setNewcode(){

        newcode = (int)(Math.random()*9999)+100;  //每次调用生成一位四位数的随机数

    }

    public static void main(String[] args) throws ClientException, InterruptedException {

        setNewcode();

        String code = Integer.toString(getNewcode());

        System.out.println("发送的验证码为："+code);

        //发短信

        SendSmsResponse response =sendSms("15806082601",code); // TODO 填写你需要测试的手机号码

        System.out.println("短信接口返回的数据----------------");

        System.out.println("Code=" + response.getCode());

        System.out.println("Message=" + response.getMessage());

        System.out.println("RequestId=" + response.getRequestId());

        System.out.println("BizId=" + response.getBizId());



       /* 消息的详细信息扩展

        System.out.println("  ==============================================  ");

        Thread.sleep(3000L);

        //查明细

        if(response.getCode() != null && response.getCode().equals("OK")) {

            QuerySendDetailsResponse querySendDetailsResponse = querySendDetails(response.getBizId());

            System.out.println("短信明细查询接口返回数据----------------");

            System.out.println("Code=" + querySendDetailsResponse.getCode());

            System.out.println("Message=" + querySendDetailsResponse.getMessage());

            int i = 0;

            for(QuerySendDetailsResponse.SmsSendDetailDTO smsSendDetailDTO : querySendDetailsResponse.getSmsSendDetailDTOs())

            {

                System.out.println("SmsSendDetailDTO["+i+"]:");

                System.out.println("Content=" + smsSendDetailDTO.getContent());

                System.out.println("ErrCode=" + smsSendDetailDTO.getErrCode());

                System.out.println("OutId=" + smsSendDetailDTO.getOutId());

                System.out.println("PhoneNum=" + smsSendDetailDTO.getPhoneNum());

                System.out.println("ReceiveDate=" + smsSendDetailDTO.getReceiveDate());

                System.out.println("SendDate=" + smsSendDetailDTO.getSendDate());

                System.out.println("SendStatus=" + smsSendDetailDTO.getSendStatus());

                System.out.println("Template=" + smsSendDetailDTO.getTemplateCode());

            }

            System.out.println("TotalCount=" + querySendDetailsResponse.getTotalCount());

            System.out.println("RequestId=" + querySendDetailsResponse.getRequestId());

        }*/



    }

}
