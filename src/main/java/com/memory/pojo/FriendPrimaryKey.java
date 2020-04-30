package com.memory.pojo;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

/**
 * @ClassName FriendPrimaryKey
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/4/23
 * @Version 1.0
 **/

@Embeddable
public class FriendPrimaryKey implements Serializable {
    private static final long serialVersionUID = -3304319243957837925L;
    private int user_id;
    private int added_id;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getAdded_id() {
        return added_id;
    }

    public void setAdded_id(int added_id) {
        this.added_id = added_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FriendPrimaryKey that = (FriendPrimaryKey) o;
        return user_id == that.user_id &&
                added_id == that.added_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, added_id);
    }

    @Override
    public String toString() {
        return "FriendPrimaryKey{" +
                "user_id=" + user_id +
                ", added_id=" + added_id +
                '}';
    }
}
