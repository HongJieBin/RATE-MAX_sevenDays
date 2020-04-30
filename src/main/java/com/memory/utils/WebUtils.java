package com.memory.utils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.UUID;

/**
 * @author hy
 * @date 2020/4/25 - 15:01
 *
 * 封装客户端提交的表单数据到formbean中,现阶段只用于RegisterFormbean
 *
 */
public class WebUtils {
    /**
     * 将request对象转换成T对象
     * @param request
     * @param clazz
     * @return
     */
    public static <T> T requestBean(HttpServletRequest request, Class<T> clazz){
        try{
            T bean = clazz.newInstance();
            Enumeration<String> e = request.getParameterNames();
            while(e.hasMoreElements()){
                String name = (String) e.nextElement();
                String value = request.getParameter(name);
                BeanUtils.setProperty(bean, name, value);
            }
            return bean;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * 生成UUID,多余方法，暂不使用，本用于生成随机的用户名
     * @return
     */
    public static String makeId(){
        return UUID.randomUUID().toString();
    }
}
