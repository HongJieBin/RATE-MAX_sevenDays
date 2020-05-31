package com.memory.dao;

import com.memory.pojo.Drift;
import com.memory.pojo.DriftEditor;

import java.util.List;

/**
 * @InterfaceName DriftEditorDAO
 * @Description TODO
 * @Author SupreacyXXXXX
 * @Date 2020/4/23
 * @Version 1.0
 **/
public interface DriftEditorDAO {
    void add(DriftEditor driftEditor);
    void update(DriftEditor driftEditor);
    void delete(DriftEditor driftEditor);
    DriftEditor get(int id);
    List<DriftEditor> getAllByDriftId(Integer driftId);
}
