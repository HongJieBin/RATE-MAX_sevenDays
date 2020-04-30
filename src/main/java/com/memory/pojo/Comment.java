package com.memory.pojo;

import javax.persistence.*;
import java.util.Objects;

/**
 * @ClassName Comment
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/4/23
 * @Version 1.0
 **/


@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private int commentId;

    @Column(name = "post_content", nullable = false, length = 128)
    private String postContent;

    @Column(name = "post_emoji", length = 256)
    private String postEmoji;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "send_id")
    private User sendUser;

    @OneToOne(targetEntity = Post.class)
    @JoinColumn(name = "post_id")
    private Post post;

    public User getSendUser() {
        return sendUser;
    }

    public void setSendUser(User sendUser) {
        this.sendUser = sendUser;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getPostEmoji() {
        return postEmoji;
    }

    public void setPostEmoji(String postEmoji) {
        this.postEmoji = postEmoji;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return commentId == comment.commentId &&
                Objects.equals(postContent, comment.postContent) &&
                Objects.equals(postEmoji, comment.postEmoji);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, postContent, postEmoji);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", postContent='" + postContent + '\'' +
                ", postEmoji='" + postEmoji + '\'' +
                ", sendUser=" + sendUser +
                ", post=" + post +
                '}';
    }
}
