package com.memory.formbean;

import com.memory.pojo.Memory;
import com.memory.pojo.User;

import java.sql.Timestamp;

/**
 * 十字记忆传输Bean
 */
public class MemoryBean {

    private UserBean user;
    private int memoryId;
    private String memoryTitle;
    private String content;
    private Timestamp memoryDate;

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public int getMemoryId() {
        return memoryId;
    }

    public void setMemoryId(int memoryId) {
        this.memoryId = memoryId;
    }

    public String getMemoryTitle() {
        return memoryTitle;
    }

    public void setMemoryTitle(String memoryTitle) {
        this.memoryTitle = memoryTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getMemoryDate() {
        return memoryDate;
    }

    public void setMemoryDate(Timestamp memoryDate) {
        this.memoryDate = memoryDate;
    }

    public static MemoryBean toMemeoryBean(Memory memory){
        MemoryBean memoryBean = new MemoryBean();
        memoryBean.setMemoryTitle(memory.getMemoryTitle());
        memoryBean.setMemoryDate(memory.getMemoryDate());
        memoryBean.setMemoryId(memory.getMemoryId());
        memoryBean.setContent(memory.getMemoryContent());
        memoryBean.setUser(UserBean.memoryToUserBean(memory));
        return memoryBean;
    }
}
