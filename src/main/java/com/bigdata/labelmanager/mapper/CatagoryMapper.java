package com.bigdata.labelmanager.mapper;

import com.bigdata.labelmanager.domain.CatagoryManager;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CatagoryMapper {

    @Select("SELECT * FROM catagory_manager")
    public List<CatagoryManager> searcherCatagory();


    @Insert("insert into catagory_manager(CATAGORY_ID,CATAGORY_NAME,CREATE_DATE,STATUS,COMMENTS) values(#{catagory_id},#{catagory_name},#{create_date},#{status},#{comments})")
    public Integer saveCatagory(CatagoryManager cataman);

    @Update("update catagory_manager set CATAGORY_ID=#{catagory_id},CATAGORY_NAME=#{catagory_name},CREATE_DATE=#{create_date},STATUS=#{status},COMMENTS=#{comments} where CATAGORY_ID=#{catagory_id}")
    public Integer UpdateCatagory(CatagoryManager cataman);


    @Select("SELECT catagory_id,catagory_name FROM catagory_manager")
    public List<CatagoryManager> searcherCatagorybyName();


    @Select("SELECT catagory_name FROM catagory_manager where catagory_id=#{id}")
    public String  searcherCatagoryNamebyID(String id);


    @Select("SELECT * FROM catagory_manager where catagory_id=#{id}")
    public List<CatagoryManager> searcherCatagorybyID(String id);



}
