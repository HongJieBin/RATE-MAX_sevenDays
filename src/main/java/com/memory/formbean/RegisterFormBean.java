package com.memory.formbean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hy
 * @date 2020/4/25 - 14:23
 */
public class RegisterFormBean {

    //RegisterFormBean中的属性与register.jsp中的表单输入项的name一一对应
//<input type="text" name="userName"/>
    private String userName;
    //<input type="password" name="userPwd"/>
    private String userPwd;
    //<input type="password" name="confirmPwd"/>
    private String confirmPwd;
    //<input type="text" name="telephoneCheck"/>      //这部分根据前端代码，只是注解样例
    private String telephoneNumber;
    //<input type="text" name="telephoneNumber"/>
    private int telephoneCheck;             //用户输入的验证码
    //<input type="text" name="systemCode"/>
    private int systemCode;        //系统内部手机验证码
    /**
     * 存储校验不通过时给用户的错误提示信息
     */
    private Map<String, String> errors = new HashMap<String, String>();
    public Map<String, String> getErrors() {
        return errors;
    }
    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
    /*
     * validate方法负责校验表单输入项
     * 表单输入项校验规则：
     * private String userName; 用户名不能为空，并且要是-的字母 abcdABcd
     * private String userPwd; 密码不能为空，并且要是-的数字
     * private String confirmPwd; 两次密码要一致
     * private int telephoneCheck;  手机号验证码验证通过
     * private String telephoneNumber; 手机号必须为13位数字，前端代码在发送短信验证码之前就可以做校验
     */
    public boolean validate() {
        boolean isOk = true;
        if (this.userName == null || this.userName.trim().equals("")) {
            isOk = false;
            errors.put("userName", "用户名不能为空！！");
        } else {
            if (!this.userName.matches("[a-zA-Z]{8}")) {
                isOk = false;
                errors.put("userName", "用户名必须是8位的字母！！");
            }
        }
        if (this.userPwd == null || this.userPwd.trim().equals("")) {
            isOk = false;
            errors.put("userPwd", "密码不能为空！！");
        } else {
            if (!this.userPwd.matches("\\d{8}")) {
                isOk = false;
                errors.put("userPwd", "密码必须是8位的数字！！");
            }
        }
// private String password; 两次密码要一致
        if (this.confirmPwd == null || this.confirmPwd.trim().equals("")) {
            isOk = false;
            errors.put("confirmPwd", "验证密码不能为空！！");
        } else {
            if (!this.confirmPwd.equals(this.userPwd)) {
                isOk = false;
                errors.put("confirmPwd", "两次密码不一致！！");
            }
        }
// private String telephoneCheck; 手机号验证码验证通过

        //TODD
        if(this.systemCode == 0 && this.telephoneCheck == 0){
            isOk = false;
            errors.put("systemCode", "系统验证码与手机验证码不能为空！！");
        }else {
            if (this.systemCode != this.telephoneCheck) {
                isOk = false;
                errors.put("telephoneCheck", "手机验证码错误！！");
            }
        }
        if (this.telephoneNumber == null || this.telephoneNumber.trim().equals("")) {
            isOk = false;
            errors.put("telephoneNumber", "手机号不能为空！！");
        } else {
            if (!this.telephoneNumber.matches("\\d{13}")) {
                isOk = false;
                errors.put("telephoneNumber", "手机号必须是13位的数字！！"); //这部分前端代码在发送短信验证码之前就可以做校验
            }
        }
        return isOk;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserPwd() {
        return userPwd;
    }
    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }
    public String getConfirmPwd() {
        return confirmPwd;
    }
    public void setConfirmPwd(String confirmPwd) {
        this.confirmPwd = confirmPwd;
    }
    public int getTelephoneCheck() {
        return telephoneCheck;
    }
    public void setTelephoneCheck(int telephoneCheck) {
        this.telephoneCheck = telephoneCheck;
    }
    public String getTelephoneNumber() {
        return telephoneNumber;
    }
    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }
    public int getSystemCode() {
        return systemCode;
    }
    public void setSystemCode(int systemCode) {
        this.systemCode = systemCode;
    }
}
