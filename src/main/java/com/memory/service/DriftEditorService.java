package com.memory.service;

import com.memory.pojo.Drift;
import com.memory.pojo.DriftEditor;

import java.util.List;

public interface DriftEditorService {

    void delete(DriftEditor driftEditor);

    void save (DriftEditor driftEditor);

    List<DriftEditor> getAllByDriftId(Integer drifId);

    void deleteAllByDriftId(Integer driftId);
}
