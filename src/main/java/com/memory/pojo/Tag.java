package com.memory.pojo;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @ClassName Tag
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/4/21
 * @Version 1.0
 **/


@Entity
@Table(name = "tag")
@DynamicInsert
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private int tagId;

    @Column(name = "tag_name", nullable = false, length = 20)
    private String tagName;

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return tagId == tag.tagId &&
                Objects.equals(tagName, tag.tagName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagId, tagName);
    }

    @Override
    public String toString() {
        return "Tag{" +
                "tagId=" + tagId +
                ", tagName='" + tagName + '\'' +
                '}';
    }


}
