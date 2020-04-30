package com.memory.pojo;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @ClassName Post
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/4/23
 * @Version 1.0
 **/


@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private int postId;

    @Column(name = "post_content",nullable = false, length = 128)
    private String postContent;

    @Column(name = "post_image")
    private String postImage;

    @Column(name = "post_date", nullable = false)
    private Timestamp postDate;

    @Column(name = "post_emoji", length = 256)
    private String postEmoji;

    @Column(name = "post_like")
    @ColumnDefault("0")
    private int postLike;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "send_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getPostImage() {
        return postImage;
    }

    public void setPostImage(String postImage) {
        this.postImage = postImage;
    }

    public Timestamp getPostDate() {
        return postDate;
    }

    public void setPostDate(Timestamp postDate) {
        this.postDate = postDate;
    }

    public String getPostEmoji() {
        return postEmoji;
    }

    public void setPostEmoji(String postEmoji) {
        this.postEmoji = postEmoji;
    }

    public int getPostLike() {
        return postLike;
    }

    public void setPostLike(int postLike) {
        this.postLike = postLike;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return postId == post.postId &&
                postLike == post.postLike &&
                Objects.equals(postContent, post.postContent) &&
                Objects.equals(postImage, post.postImage) &&
                Objects.equals(postDate, post.postDate) &&
                Objects.equals(postEmoji, post.postEmoji);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, postContent, postImage, postDate, postEmoji, postLike);
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", postContent='" + postContent + '\'' +
                ", postImage='" + postImage + '\'' +
                ", postDate=" + postDate +
                ", postEmoji='" + postEmoji + '\'' +
                ", postLike=" + postLike +
                ", user=" + user +
                '}';
    }
}
