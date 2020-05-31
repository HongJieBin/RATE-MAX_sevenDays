package com.memory.dao;

import com.memory.pojo.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName ReportDAOImpl
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/4/23
 * @Version 1.0
 **/

@Repository
@Transactional
public class ReportDAOImpl implements ReportDAO{
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public void add(Report report) {
        hibernateTemplate.save(report);
    }

    @Override
    public void update(Report report) {
        hibernateTemplate.update(report);
    }

    @Override
    public void delete(Report report) {
        hibernateTemplate.delete(report);
    }

    @Override
    public Report get(int id) {
        return hibernateTemplate.get(Report.class, id);
    }

    @Override
    public List<Report> getAll() {
        return (List<Report>)hibernateTemplate.find("from Report");
    }

    @Override
    public List<Report> getByReason(int reportReason) {
        return (List<Report>)hibernateTemplate.find("from Report as r where r.reportReason = ?",reportReason);
    }

    @Override
    public List<Report> getByType(int reportType) {
        return (List<Report>)hibernateTemplate.find("from Report as r where r.reportTypeId = ?",reportType);
    }

    @Override
    public List<Report> getByTypeAndReason(int reportType, int reportReason) {
        return (List<Report>)hibernateTemplate.find("from Report as r where r.reportReason =? " +
                "and r.reportTypeId = ?",new Object[]{reportType,reportReason});
    }
}
