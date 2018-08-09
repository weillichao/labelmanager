package com.bigdata.labelmanager.domain;

/*
   标签种类实体
 */
public class CatagoryManager {

   //类别id
   private String catagory_id;
   //类别名称
   private String catagory_name;
   //创建日期
   private String create_date;
   //备注
   private String comments;
   //状态
   private String status;



   public String getCatagory_id() {
      return catagory_id;
   }

   public void setCatagory_id(String catagory_id) {
      this.catagory_id = catagory_id;
   }

   public String getCatagory_name() {
      return catagory_name;
   }

   public void setCatagory_name(String catagory_name) {
      this.catagory_name = catagory_name;
   }

   public String getCreate_date() {
      return create_date;
   }

   public void setCreate_date(String create_date) {
      this.create_date = create_date;
   }

   public String getComments() {
      return comments;
   }

   public void setComments(String comments) {
      this.comments = comments;
   }

   public String getStatus() {
      return status;
   }

   public void setStatus(String status) {
      this.status = status;
   }


}
