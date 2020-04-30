package com.memory.pojo;


import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_tag")
@IdClass(UserTagPK.class)
public class UserTag {

    @Id
    @Column(name = "user_id")
    private Integer userId;

    @Id
    @Column(name = "tag_id")
    private Integer tagId;

    @Column(name = "tag_number")
    @ColumnDefault(value = "0")
    private Integer tagNumber;

    @Override
    public String toString() {
        return "UserTagDAO{" +
                "userId=" + userId +
                ", tagId=" + tagId +
                ", tagNumber=" + tagNumber +
                '}';
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public Integer getTagNumber() {
        return tagNumber;
    }

    public void setTagNumber(Integer tagNumber) {
        this.tagNumber = tagNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserTag userTag = (UserTag) o;
        return Objects.equals(userId, userTag.userId) &&
                Objects.equals(tagId, userTag.tagId) &&
                Objects.equals(tagNumber, userTag.tagNumber);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, tagId, tagNumber);
    }
}
