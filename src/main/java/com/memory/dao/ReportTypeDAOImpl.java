package com.memory.dao;

import com.memory.pojo.Report;
import com.memory.pojo.ReportType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName ReportTypeDAOImpl
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/5/25
 * @Version 1.0
 **/

@Repository
@Transactional
public class ReportTypeDAOImpl implements ReportTypeDAO{
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public void add(ReportType reportType) {
        hibernateTemplate.save(reportType);
    }

    @Override
    public void update(ReportType reportType) {
        hibernateTemplate.update(reportType);
    }

    @Override
    public void delete(ReportType reportType) {
        hibernateTemplate.delete(reportType);
    }

    @Override
    public ReportType get(int id) {
        return hibernateTemplate.get(ReportType.class, id);
    }
}
