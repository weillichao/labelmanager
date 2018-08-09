package com.bigdata.labelmanager.service;

import com.bigdata.labelmanager.domain.CatagoryManager;
import com.bigdata.labelmanager.domain.LabelManager;
import com.bigdata.labelmanager.mapper.CatagoryMapper;
import com.bigdata.labelmanager.mapper.LabelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabelManagerService {


    @Autowired
    LabelMapper labelMapper;

    /***
     * 查询标签类别
     * @return List<CatagoryManager>
     */
    public List<LabelManager> searcherLabel() {
        return labelMapper.searcherLabel();
    }

    /***
     * 插入标签类别
     * @return String
     */
    public String InsertLabel(LabelManager cm) {
        return labelMapper.saveLabel(cm).toString();
    }
    /***
     * 更新标签类别
     * @return String
     */
    public String UpdateLabel(LabelManager cm) {
        return labelMapper.UpdateLabel(cm).toString();
    }

    public List<LabelManager> searcherLabelName()
    {
        return labelMapper.searcherLabelName();
    }
    public List<LabelManager> searcherLabelNamebyID(String id)
    {
        return labelMapper.searcherLabelNamebyID(id);
    }
    public List<LabelManager> searcherLabelNamebyCatagoryID(String id)
    {return labelMapper.searcherLabelNamebyCatagoryID(id);}


}
