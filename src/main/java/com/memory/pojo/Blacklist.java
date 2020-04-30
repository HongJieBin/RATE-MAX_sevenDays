package com.memory.pojo;

import javax.persistence.*;
import java.util.Objects;

/**
 * @ClassName Blacklist
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/4/23
 * @Version 1.0
 **/


@Entity
@Table(name = "blacklist")
@IdClass(BlacklistPK.class)
public class Blacklist {
    @Id
    @Column(name = "user_id")
    private int userId;

    @Id
    @Column(name = "added_id")
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
        Blacklist blacklist = (Blacklist) o;
        return userId == blacklist.userId &&
                addedId == blacklist.addedId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, addedId);
    }


    @Override
    public String toString() {
        return "Blacklist{" +
                "userId=" + userId +
                ", addedId=" + addedId +
                '}';
    }
}
