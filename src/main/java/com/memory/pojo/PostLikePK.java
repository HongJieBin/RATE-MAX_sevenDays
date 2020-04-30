package com.memory.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * @ClassName PostLikePK
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/4/23
 * @Version 1.0
 **/


public class PostLikePK implements Serializable {
    @Column(name = "post_id")
    @Id
    private int postId;

    @Column(name = "user_id")
    @Id
    private int userId;


    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostLikePK that = (PostLikePK) o;
        return postId == that.postId &&
                userId == that.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, userId);
    }

    @Override
    public String toString() {
        return "PostLikePK{" +
                "postId=" + postId +
                ", userId=" + userId +
                '}';
    }
}
