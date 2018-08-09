package com.bigdata.labelmanager.mapper;

import com.bigdata.labelmanager.domain.LabelManager;
import com.bigdata.labelmanager.domain.LabelValueManager;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface LabelValueMapper {

    @Select("SELECT * FROM labelvalue_manager ")
    public List<LabelValueManager> searcherLabelValue();


    @Insert("insert into labelvalue_manager(ID,LABEL_ID,LABEL_VALUE,PARENT_ID,OPERATION,CREATE_DATE,STATUS,COMMENTS,LABEL_NAME) values(#{id},#{label_id},#{label_value},#{parent_id},#{operation},#{create_date},#{status},#{comments},#{label_name})")
    public Integer saveLabel(LabelValueManager lbmanager);

    @Update("update  labelvalue_manager set LABEL_ID=#{label_id},LABEL_VALUE=#{label_value},PARENT_ID=#{parent_id},OPERATION=#{operation},CREATE_DATE=#{create_date},STATUS=#{status},COMMENTS=#{comments},LABEL_NAME=#{label_name} where ID=#{id}")
    public Integer UpdateLabel(LabelValueManager lbmanager);


    @Select("SELECT * FROM labelvalue_manager where label_id=#{id} and status='ACTIVE'")
    public List<LabelValueManager> searcherLabelValuebyID(String id);

    @Select("SELECT * FROM labelvalue_manager where id=#{id} ")
    public LabelValueManager searcherLabelValueNamebyID(String id);

}
