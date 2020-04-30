package com.memory.pojo;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Objects;

/**
 * @ClassName Bug
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/4/23
 * @Version 1.0
 **/


@Entity
@Table(name = "bug")
@DynamicInsert
public class Bug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bug_id")
    private int bugId;

    @Column(name = "bug_content", nullable = false, length = 128)
    private String bugContent;


    public int getBugId() {
        return bugId;
    }

    public void setBugId(int bugId) {
        this.bugId = bugId;
    }

    public String getBugContent() {
        return bugContent;
    }

    public void setBugContent(String bugContent) {
        this.bugContent = bugContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bug bug = (Bug) o;
        return bugId == bug.bugId &&
                Objects.equals(bugContent, bug.bugContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bugId, bugContent);
    }

    @Override
    public String toString() {
        return "Bug{" +
                "bugId=" + bugId +
                ", bugContent='" + bugContent + '\'' +
                '}';
    }
}
