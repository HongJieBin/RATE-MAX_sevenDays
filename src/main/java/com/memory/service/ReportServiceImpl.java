package com.memory.service;

import com.memory.dao.ReportDAO;
import com.memory.pojo.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {


    @Autowired
    private ReportDAO reportDAO;

    @Override
    public List<Report> getReport(int reportType, int reportReason) {
        List<Report> list;
        if(reportType != 0 && reportReason != 0){               //获取特定类型和原因的举报
            list = reportDAO.getByTypeAndReason(reportType,reportReason);
        }else if(reportReason != 0){                            //获取特定原因的举报
            list = reportDAO.getByReason(reportReason);
        }else if(reportType != 0){                              //获取特定类型的举报
            list = reportDAO.getByType(reportType);
        }else{                                                  //获取所有举报
            list = reportDAO.getAll();
        }
        return list;
    }
}
