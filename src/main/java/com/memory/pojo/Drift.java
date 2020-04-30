package com.memory.pojo;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @ClassName Drift
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/4/23
 * @Version 1.0
 **/


@Entity
@Table(name = "drift")
@DynamicInsert
public class Drift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bottle_id")
    private int bottleId;

    @Column(name = "content", nullable = false, length = 128)
    private String content;

    @Column(name = "flag", nullable = false)
    private byte flag;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany
    @JoinColumn(name = "edit_id")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private Set<DriftEditor> driftEditors = new HashSet<>();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<DriftEditor> getDriftEditors() {
        return driftEditors;
    }

    public void setDriftEditors(Set<DriftEditor> driftEditors) {
        this.driftEditors = driftEditors;
    }

    public int getBottleId() {
        return bottleId;
    }

    public void setBottleId(int bottleId) {
        this.bottleId = bottleId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public byte getFlag() {
        return flag;
    }

    public void setFlag(byte flag) {
        this.flag = flag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Drift drift = (Drift) o;
        return bottleId == drift.bottleId &&
                flag == drift.flag &&
                Objects.equals(content, drift.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bottleId, content, flag);
    }

    @Override
    public String toString() {
        return "Drift{" +
                "bottleId=" + bottleId +
                ", content='" + content + '\'' +
                ", flag=" + flag +
                ", user=" + user +
                ", driftEditors=" + driftEditors +
                '}';
    }
}
