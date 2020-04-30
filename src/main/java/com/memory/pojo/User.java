package com.memory.pojo;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @ClassName User
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/4/21
 * @Version 1.0
 **/

@Entity
@Table(name = "user")
@DynamicInsert
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "telephone", nullable = false, length = 14)
    private String telephone;

    @Column(name = "nickname", nullable = false, length = 20)
    private String nickname;

    @Column(name = "password", nullable = false, length = 32)
    private String password;

    @Column(name = "this_week_tag")
    private String thisWeekTag;

    @Column(name = "icon")
    private String icon;

    @Column(name = "profile", nullable = false, length = 256)
    private String profile;

    @Column(name = "gender")
    @ColumnDefault("'保密'")
    private String gender;

    @Column(name = "salvage_number")
    @ColumnDefault("0")
    private Integer salvageNumber;

    @Column(name = "throw_number")
    @ColumnDefault("0")
    private Integer throwNumber;


    @OneToMany(mappedBy = "user",targetEntity = Memory.class)
    @Cascade(CascadeType.DELETE)
    private Set<Memory> memories = new HashSet<>();     //用户十字记忆记录表

    @ManyToMany(targetEntity = Tag.class)
    @Fetch(FetchMode.JOIN)
    @JoinTable(name = "user_tag",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags = new HashSet<Tag>();     //用户标签表

    @OneToMany(mappedBy = "user", targetEntity = Ban.class)
    @Cascade(CascadeType.DELETE)
    private Set<Ban> banHistory = new HashSet<Ban>();   //封禁记录表

    @OneToMany(mappedBy = "user", targetEntity = Drift.class)
    @Cascade(CascadeType.DELETE)
    private Set<Drift> drifts = new HashSet<Drift>();   //漂流瓶表

    @OneToMany(mappedBy = "user", targetEntity = Chatroom.class)
    @Cascade(CascadeType.DELETE)
    private Set<Chatroom> chatrooms = new HashSet<Chatroom>();   //漂流瓶表

    @OneToMany(mappedBy = "user", targetEntity = Post.class)
    @Cascade(CascadeType.DELETE)
    private Set<Post> posts = new HashSet<Post>();   //动态表

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getThisWeekTag() {
        return thisWeekTag;
    }

    public void setThisWeekTag(String thisWeekTag) {
        this.thisWeekTag = thisWeekTag;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getSalvageNumber() {
        return salvageNumber;
    }

    public void setSalvageNumber(Integer salvageNumber) {
        this.salvageNumber = salvageNumber;
    }

    public Integer getThrowNumber() {
        return throwNumber;
    }

    public void setThrowNumber(Integer throwNumber) {
        this.throwNumber = throwNumber;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }



    public Set<Memory> getMemories() {
        return memories;
    }

    public void setMemories(Set<Memory> memories) {
        this.memories = memories;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId &&
                salvageNumber == user.salvageNumber &&
                throwNumber == user.throwNumber &&
                Objects.equals(telephone, user.telephone) &&
                Objects.equals(nickname, user.nickname) &&
                Objects.equals(password, user.password) &&
                Objects.equals(thisWeekTag, user.thisWeekTag) &&
                Objects.equals(icon, user.icon) &&
                Objects.equals(profile, user.profile) &&
                Objects.equals(gender, user.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, telephone, nickname, password, thisWeekTag, icon, profile, gender, salvageNumber, throwNumber);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", telephone='" + telephone + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", thisWeekTag='" + thisWeekTag + '\'' +
                ", icon='" + icon + '\'' +
                ", profile='" + profile + '\'' +
                ", gender='" + gender + '\'' +
                ", salvageNumber=" + salvageNumber +
                ", throwNumber=" + throwNumber +
                '}';
    }
}
