package com.memory.formbean;

import com.memory.pojo.DriftEditor;

import java.sql.Timestamp;

public class DriftEditorBean {

    private int editId;

    private String comment;

    private Timestamp editDate;

    private int bottleId;

    private int userId;

    public int getEditId() {
        return editId;
    }

    public void setEditId(int editId) {
        this.editId = editId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getEditDate() {
        return editDate;
    }

    public void setEditDate(Timestamp editDate) {
        this.editDate = editDate;
    }

    public int getBottleId() {
        return bottleId;
    }

    public void setBottleId(int bottleId) {
        this.bottleId = bottleId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public static DriftEditorBean toDriftEditorBean(DriftEditor driftEditor){
        DriftEditorBean driftEditorBean = new DriftEditorBean();
        driftEditorBean.setBottleId(driftEditor.getDrift().getBottleId());
        driftEditorBean.setComment(driftEditor.getComment());
        driftEditorBean.setEditDate(driftEditor.getEditDate());
        driftEditorBean.setEditId(driftEditor.getEditId());
        driftEditorBean.setUserId(driftEditor.getUserId());
        return driftEditorBean;
    }
}
