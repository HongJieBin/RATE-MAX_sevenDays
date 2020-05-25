package com.memory.dao;

import com.memory.pojo.Report;
import com.memory.pojo.ReportReason;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName ReportReasonDAOImpl
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/5/25
 * @Version 1.0
 **/

@Repository
@Transactional
public class ReportReasonDAOImpl implements ReportReasonDAO{
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public void add(ReportReason reportReason) {
        hibernateTemplate.save(reportReason);
    }

    @Override
    public void update(ReportReason reportReason) {
        hibernateTemplate.update(reportReason);
    }

    @Override
    public void delete(ReportReason reportReason) {
        hibernateTemplate.delete(reportReason);
    }

    @Override
    public ReportReason get(int id) {
        return hibernateTemplate.get(ReportReason.class, id);
    }
}
