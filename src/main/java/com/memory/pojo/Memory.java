package com.memory.pojo;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

/**
 * @ClassName Memory
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/4/21
 * @Version 1.0
 **/


@Entity
@Table(name = "memory")
@DynamicInsert
public class Memory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memory_id")
    private int memoryId;

    @Column(name = "memory_title", nullable = false, length = 20)
    private String memoryTitle;

    @Column(name = "memory_content", nullable = false, length = 100)
    private String memoryContent;

    @Column(name = "memory_date", nullable = false)
    private Timestamp memoryDate;

    //memory的发布用户

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    public int getMemoryId() {
        return memoryId;
    }

    public void setMemoryId(int memoryId) {
        this.memoryId = memoryId;
    }

    public String getMemoryTitle() {
        return memoryTitle;
    }

    public void setMemoryTitle(String memoryTitle) {
        this.memoryTitle = memoryTitle;
    }

    public String getMemoryContent() {
        return memoryContent;
    }

    public void setMemoryContent(String memoryContent) {
        this.memoryContent = memoryContent;
    }

    public Timestamp getMemoryDate() {
        return memoryDate;
    }

    public void setMemoryDate(Timestamp memoryDate) {
        this.memoryDate = memoryDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Memory memory = (Memory) o;
        return memoryId == memory.memoryId &&
                Objects.equals(memoryTitle, memory.memoryTitle) &&
                Objects.equals(memoryContent, memory.memoryContent) &&
                Objects.equals(memoryDate, memory.memoryDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memoryId, memoryTitle, memoryContent, memoryDate);
    }

    @Override
    public String toString() {
        return "Memory{" +
                "memoryId=" + memoryId +
                ", memoryTitle='" + memoryTitle + '\'' +
                ", memoryContent='" + memoryContent + '\'' +
                ", memoryDate=" + memoryDate +
                '}';
    }
}
