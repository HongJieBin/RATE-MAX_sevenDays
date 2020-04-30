package com.memory.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * @ClassName BlacklistPK
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/4/23
 * @Version 1.0
 **/


public class BlacklistPK implements Serializable {
    @Column(name = "user_id")
    @Id
    private int userId;

    @Column(name = "added_id")
    @Id
    private int addedId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlacklistPK that = (BlacklistPK) o;
        return userId == that.userId &&
                addedId == that.addedId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, addedId);
    }

    @Override
    public String toString() {
        return "BlacklistPK{" +
                "userId=" + userId +
                ", addedId=" + addedId +
                '}';
    }
}
