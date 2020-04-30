package com.memory.pojo;

import javax.persistence.*;
import java.util.Objects;

/**
 * @ClassName PostLike
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/4/23
 * @Version 1.0
 **/


@Entity
@Table(name = "post_like")
@IdClass(PostLikePK.class)
public class PostLike {
    @Id
    @Column(name = "post_id")
    private int postId;

    @Id
    @Column(name = "user_id")
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
        PostLike postLike = (PostLike) o;
        return postId == postLike.postId &&
                userId == postLike.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, userId);
    }

    @Override
    public String toString() {
        return "PostLike{" +
                "postId=" + postId +
                ", userId=" + userId +
                '}';
    }
}
