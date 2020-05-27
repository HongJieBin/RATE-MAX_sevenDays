package com.memory.controller.VO;

public class TagSortVO {
    private Integer userID;
    private Integer firstTagID;
    private Integer secondTagID;
    private Integer thirdTagID;

    public Integer getFirstTagID() {
        return firstTagID;
    }

    public Integer getSecondTagID() {
        return secondTagID;
    }

    public Integer getThirdTagID() {
        return thirdTagID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setFirstTagID(Integer firstTagID) {
        this.firstTagID = firstTagID;
    }

    public void setSecondTagID(Integer secondTagID) {
        this.secondTagID = secondTagID;
    }

    public void setThirdTagID(Integer thirdTagID) {
        this.thirdTagID = thirdTagID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }
}
