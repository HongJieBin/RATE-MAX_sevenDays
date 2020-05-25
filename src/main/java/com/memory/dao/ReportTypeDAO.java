package com.memory.dao;

import com.memory.pojo.Report;
import com.memory.pojo.ReportType;

/**
 * @InterfaceName ReportTypeDAO
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/5/25
 * @Version 1.0
 **/
public interface ReportTypeDAO {
    void add(ReportType reportType);
    void update(ReportType reportType);
    void delete(ReportType reportType);
    ReportType get(int id);
}
