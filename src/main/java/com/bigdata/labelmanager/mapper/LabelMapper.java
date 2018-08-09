package com.bigdata.labelmanager.mapper;

import com.bigdata.labelmanager.domain.CatagoryManager;
import com.bigdata.labelmanager.domain.LabelManager;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface LabelMapper {





    @Select("SELECT * FROM label_manager")
    public List<LabelManager> searcherLabel();


    @Insert("insert into label_manager(LABEL_ID,LABEL_NAME,CATAGORY_ID,CREATE_DATE,UPDATE_DATE,CREATE_WAY,STATUS,COMMENTS,CATAGORY_NAME,LABEL_ALIAS) values(#{label_id},#{label_name},#{catagory_id},#{create_date},#{update_date},#{create_way},#{status},#{comments},#{catagory_name},#{label_alias})")
    public Integer saveLabel(LabelManager cataman);

    @Update("update label_manager set LABEL_NAME=#{label_name},CATAGORY_ID=#{catagory_id},CREATE_DATE=#{create_date},UPDATE_DATE=#{update_date},CREATE_WAY=#{create_way},STATUS=#{status},COMMENTS=#{comments},CATAGORY_NAME=#{catagory_name},LABEL_ALIAS=#{label_alias} where LABEL_ID=#{label_id}")
    public Integer UpdateLabel(LabelManager cataman);


    @Select("SELECT label_id,label_name FROM label_manager")
    public List<LabelManager> searcherLabelName();


    @Select("SELECT * FROM label_manager where label_id=#{id}")
    public List<LabelManager> searcherLabelNamebyID(String id);


    @Select("SELECT * FROM label_manager where catagory_id=#{id}")
    public List<LabelManager> searcherLabelNamebyCatagoryID(String id);
}
