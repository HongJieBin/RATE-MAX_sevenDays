package com.memory.pojo;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @ClassName Report
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/4/23
 * @Version 1.0
 **/


@Entity
@Table(name = "report")
@DynamicInsert
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private int reportId;

    @Column(name = "report_reason", nullable = false, length = 20)
    private String reportReason;

    @Column(name = "report_content", length = 128)
    private String reportContent;

    @Column(name = "report_date", nullable = false)
    private Timestamp reportDate;

    @Column(name = "report_type_id", nullable = false, length = 3)
    private Integer reportTypeId;

    @Column(name = "send_id", nullable = false)
    private Integer sendId;

    @Column(name = "reported_id", nullable = false)
    private Integer reportedId;

    public Integer getSendId() {
        return sendId;
    }

    public void setSendId(Integer sendId) {
        this.sendId = sendId;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public String getReportReason() {
        return reportReason;
    }

    public void setReportReason(String reportReason) {
        this.reportReason = reportReason;
    }

    public String getReportContent() {
        return reportContent;
    }

    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }

    public Timestamp getReportDate() {
        return reportDate;
    }

    public void setReportDate(Timestamp reportDate) {
        this.reportDate = reportDate;
    }

    public Integer getReportTypeId() {
        return reportTypeId;
    }

    public void setReportTypeId(Integer reportTypeId) {
        this.reportTypeId = reportTypeId;
    }

    public Integer getReportedId() {
        return reportedId;
    }

    public void setReportedId(Integer reportedId) {
        this.reportedId = reportedId;
    }

    @Override
    public String toString() {
        return "Report{" +
                "reportId=" + reportId +
                ", reportReason='" + reportReason + '\'' +
                ", reportContent='" + reportContent + '\'' +
                ", reportDate=" + reportDate +
                ", reportTypeId=" + reportTypeId +
                ", sendId=" + sendId +
                ", reportedId=" + reportedId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return reportId == report.reportId &&
                Objects.equals(reportReason, report.reportReason) &&
                Objects.equals(reportContent, report.reportContent) &&
                Objects.equals(reportDate, report.reportDate) &&
                Objects.equals(reportTypeId, report.reportTypeId) &&
                Objects.equals(sendId, report.sendId) &&
                Objects.equals(reportedId, report.reportedId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reportId, reportReason, reportContent, reportDate, reportTypeId, sendId, reportedId);
    }
}
