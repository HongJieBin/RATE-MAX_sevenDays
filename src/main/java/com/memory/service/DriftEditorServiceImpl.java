package com.memory.service;

import com.memory.dao.DriftEditorDAO;
import com.memory.pojo.DriftEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriftEditorServiceImpl implements DriftEditorService {


    @Autowired
    private DriftEditorDAO  driftEditorDAO;

    @Override
    public void delete(DriftEditor driftEditor){
        driftEditorDAO.delete(driftEditor);
    }

    @Override
    public void save(DriftEditor driftEditor) {
        driftEditorDAO.add(driftEditor);
    }

    @Override
    public List<DriftEditor> getAllByDriftId(Integer drifId) {
        return driftEditorDAO.getAllByDriftId(drifId);
    }

    @Override
    public void deleteAllByDriftId(Integer driftId) {
        List<DriftEditor> list = getAllByDriftId(driftId);
        for(DriftEditor driftEditor : list){
            driftEditorDAO.delete(driftEditor);
        }
    }
}
