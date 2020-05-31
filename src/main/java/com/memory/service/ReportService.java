package com.memory.service;

import com.memory.pojo.Report;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface ReportService {

    List<Report> getReport(int reportType,int reportReason);
}
