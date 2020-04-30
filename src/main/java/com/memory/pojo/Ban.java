package com.memory.pojo;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @ClassName Ban
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/4/23
 * @Version 1.0
 **/


@Entity
@Table(name = "ban")
@DynamicInsert
public class Ban {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ban_id")
    private int banId;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "ban_stime", nullable = false)
    private Timestamp banStime;

    @Column(name = "ban_etime", nullable = false)
    private Timestamp banEtime;

    public int getBanId() {
        return banId;
    }

    public void setBanId(int banId) {
        this.banId = banId;
    }

    public Timestamp getBanStime() {
        return banStime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setBanStime(Timestamp banStime) {
        this.banStime = banStime;
    }

    public Timestamp getBanEtime() {
        return banEtime;
    }

    public void setBanEtime(Timestamp banEtime) {
        this.banEtime = banEtime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ban ban = (Ban) o;
        return banId == ban.banId &&
                Objects.equals(banStime, ban.banStime) &&
                Objects.equals(banEtime, ban.banEtime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(banId, banStime, banEtime);
    }

    @Override
    public String toString() {
        return "Ban{" +
                "banId=" + banId +
                ", user=" + user +
                ", banStime=" + banStime +
                ", banEtime=" + banEtime +
                '}';
    }
}
