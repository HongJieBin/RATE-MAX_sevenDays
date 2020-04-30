package com.memory.pojo;

import javax.persistence.*;
import java.util.Objects;

/**
 * @ClassName Friend
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/4/23
 * @Version 1.0
 **/


@Entity
@Table(name = "friend")
@IdClass(FriendPK.class)
public class Friend {
    @Id
    @Column(name = "user_id")
    private int userId;

    @Id
    @Column(name = "added_id")
    private int addedId;

    @Column(name = "remark", length = 64)
    private String remark;

    @Column(name = "level", nullable = false)
    private int level;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAddedId() {
        return addedId;
    }

    public void setAddedId(int addedId) {
        this.addedId = addedId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Friend friend = (Friend) o;
        return userId == friend.userId &&
                addedId == friend.addedId &&
                level == friend.level &&
                Objects.equals(remark, friend.remark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, addedId, remark, level);
    }

    @Override
    public String toString() {
        return "Friend{" +
                "userId=" + userId +
                ", addedId=" + addedId +
                ", remark='" + remark + '\'' +
                ", level=" + level +
                '}';
    }
}
