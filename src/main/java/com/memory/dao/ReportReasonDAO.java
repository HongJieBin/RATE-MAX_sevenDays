package com.memory.dao;

import com.memory.pojo.Report;
import com.memory.pojo.ReportReason;

/**
 * @InterfaceName ReportReasonDAO
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/5/25
 * @Version 1.0
 **/
public interface ReportReasonDAO {
    void add(ReportReason reportReason);
    void update(ReportReason reportReason);
    void delete(ReportReason reportReason);
    ReportReason get(int id);
}
