package com.memory.dao;

import com.memory.pojo.Admin;
import com.memory.pojo.Report;

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
}
