package com.bigdata.labelmanager.service;

import com.bigdata.labelmanager.domain.CatagoryManager;
import com.bigdata.labelmanager.mapper.CatagoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatagoryManagerService {

    @Autowired
    CatagoryMapper catagoryMapper;

    /***
     * 查询标签类别
     * @return List<CatagoryManager>
     */
    public List<CatagoryManager> searcherCatagory() {
        return catagoryMapper.searcherCatagory();
    }

    /***
     * 插入标签类别
     * @return String
     */
    public String InsertCatagory(CatagoryManager cm) {
        return catagoryMapper.saveCatagory(cm).toString();
    }
    /***
     * 更新标签类别
     * @return String
     */
    public String UpdateCatagory(CatagoryManager cm) {
        return catagoryMapper.UpdateCatagory(cm).toString();
    }

    public List<CatagoryManager> searcherCatagoryName() {
        return catagoryMapper.searcherCatagorybyName();
    }

    public String searcherCatagoryNamebyID(String id)
    {
        return catagoryMapper.searcherCatagoryNamebyID(id);
    }

    public List<CatagoryManager> searcherCatagorybyID(String id) {
        return catagoryMapper.searcherCatagorybyID(id);
    }


}
