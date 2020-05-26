package com.memory.pojo;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Objects;

/**
 * @ClassName ReportType
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/5/25
 * @Version 1.0
 **/

@Entity
@Table(name = "report_type")
@DynamicInsert
public class ReportType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "report_type", nullable = false, length = 20)
    private String reportType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportType that = (ReportType) o;
        return id == that.id &&
                Objects.equals(reportType, that.reportType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reportType);
    }

    @Override
    public String toString() {
        return "ReportType{" +
                "id=" + id +
                ", reportType='" + reportType + '\'' +
                '}';
    }
}
