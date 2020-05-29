package com.memory.formbean;

import com.memory.pojo.Drift;
import com.memory.pojo.DriftEditor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DriftBean {

    private int bottleId;
    private String content;
    private byte flag;
    private UserBean userBean;
    private List<DriftEditor> comments;

    public int getBottleId() {
        return bottleId;
    }

    public void setBottleId(int bottleId) {
        this.bottleId = bottleId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public byte getFlag() {
        return flag;
    }

    public void setFlag(byte flag) {
        this.flag = flag;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }


    public static DriftBean toDriftBean(Drift drift){
        DriftBean driftBean = new DriftBean();
        driftBean.setBottleId(drift.getBottleId());
        driftBean.setContent(drift.getContent());
        driftBean.setFlag(drift.getFlag());
        driftBean.setUserBean(UserBean.toUserBean(drift.getUser()));
        return driftBean;
    }

    public List<DriftEditor> getComments() {
        return comments;
    }

    public void setComments(List<DriftEditor> comments) {
        this.comments = comments;
    }
}
