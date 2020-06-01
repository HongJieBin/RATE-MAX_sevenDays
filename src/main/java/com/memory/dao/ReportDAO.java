package com.memory.dao;

import com.memory.pojo.Admin;
import com.memory.pojo.Report;

import java.util.List;

/**
 * @InterfaceName ReportDAO
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/4/23
 * @Version 1.0
 **/
public interface ReportDAO {
    void add(Report report);
    void update(Report report);
    void delete(Report report);
    Report get(int id);
    List<Report> getByReason(int reportReason);
    List<Report> getByType(int reportType);
    List<Report> getAll();
    List<Report> getByTypeAndReason(int reportType,int reportReason);
}
