package com.memory.dao;

import com.memory.pojo.Drift;
import com.memory.pojo.DriftEditor;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @ClassName DriftEditorDAOImpl
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/4/23
 * @Version 1.0
 **/

@Repository
@Transactional
public class DriftEditorDAOImpl implements DriftEditorDAO{
    @Resource
    private HibernateTemplate hibernateTemplate;

    @Override
    public void add(DriftEditor driftEditor) {
        hibernateTemplate.save(driftEditor);
    }

    @Override
    public void update(DriftEditor driftEditor) {
        hibernateTemplate.update(driftEditor);
    }

    @Override
    public void delete(DriftEditor driftEditor) {
        hibernateTemplate.delete(driftEditor);
    }

    @Override
    public DriftEditor get(int id) {
        return hibernateTemplate.get(DriftEditor.class, id);
    }
}
