package com.memory.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * UserTag表联合主键类
 */

public class UserTagPK implements Serializable {

    @Column( name = "user_id")
    @Id
    private Integer userId;

    @Column( name = "tag_id")
    @Id
    private Integer tagId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserTagPK userTagPK = (UserTagPK) o;
        return Objects.equals(userId, userTagPK.userId) &&
                Objects.equals(tagId, userTagPK.tagId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, tagId);
    }
}
