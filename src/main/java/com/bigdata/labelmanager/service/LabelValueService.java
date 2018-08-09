package com.bigdata.labelmanager.service;

import com.bigdata.labelmanager.domain.LabelManager;
import com.bigdata.labelmanager.domain.LabelValueManager;
import com.bigdata.labelmanager.mapper.LabelMapper;
import com.bigdata.labelmanager.mapper.LabelValueMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabelValueService {
    @Autowired
    LabelValueMapper labelvalueMapper;

    /***
     * 查询标签类别
     * @return List<CatagoryManager>
     */
    public List<LabelValueManager> searcherLabel() {
        return labelvalueMapper.searcherLabelValue();
    }

    /***
     * 插入标签类别
     * @return String
     */
    public String InsertLabel(LabelValueManager cm) {
        return labelvalueMapper.saveLabel(cm).toString();
    }
    /***
     * 更新标签类别
     * @return String
     */
    public String UpdateLabel(LabelValueManager cm) {
        return labelvalueMapper.UpdateLabel(cm).toString();
    }

    public List<LabelValueManager> searcherLabelbyID(String id) {
        return labelvalueMapper.searcherLabelValuebyID(id);
    }
    public LabelValueManager searcherLabelValueNamebyID(String id)
    {
        return labelvalueMapper.searcherLabelValueNamebyID(id);
    }
}
