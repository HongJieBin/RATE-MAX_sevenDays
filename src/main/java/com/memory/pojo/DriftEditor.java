package com.memory.pojo;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @ClassName DriftEditor
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/4/23
 * @Version 1.0
 **/


@Entity
@Table(name = "drift")
@DynamicInsert
public class DriftEditor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "edit_id")
    private int editId;

    @Column(name = "comment", nullable = false, length = 128)
    private String comment;

    @Column(name = "edit_date", nullable = false)
    private Timestamp editDate;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    public int getEditId() {
        return editId;
    }

    public void setEditId(int editId) {
        this.editId = editId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getEditDate() {
        return editDate;
    }

    public void setEditDate(Timestamp editDate) {
        this.editDate = editDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DriftEditor that = (DriftEditor) o;
        return editId == that.editId &&
                Objects.equals(comment, that.comment) &&
                Objects.equals(editDate, that.editDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(editId, comment, editDate);
    }

    @Override
    public String toString() {
        return "DriftEditor{" +
                "editId=" + editId +
                ", comment='" + comment + '\'' +
                ", editDate=" + editDate +
                ", userId=" + userId +
                '}';
    }
}
