package com.bigdata.labelmanager.domain;

public class LabelManager {

    private String label_id;
    private String label_name;
    private String catagory_id;
    private String create_date;
    private String catagory_name;
    private String update_date;
    private String comments;
    private String status;
    private String create_way;
    private String label_alias;


    public String getLabel_alias() {
        return label_alias;
    }

    public void setLabel_alias(String label_alias) {
        this.label_alias = label_alias;
    }

    public String getLabel_id() {
        return label_id;
    }

    public void setLabel_id(String label_id) {
        this.label_id = label_id;
    }

    public String getLabel_name() {
        return label_name;
    }

    public void setLabel_name(String label_name) {
        this.label_name = label_name;
    }

    public String getCatagory_id() {
        return catagory_id;
    }

    public void setCatagory_id(String catagory_id) {
        this.catagory_id = catagory_id;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(String update_date) {
        this.update_date = update_date;
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

    public String getCreate_way() {
        return create_way;
    }

    public void setCreate_way(String create_way) {
        this.create_way = create_way;
    }

    public String getCatagory_name() {
        return catagory_name;
    }

    public void setCatagory_name(String catagory_name) {
        this.catagory_name = catagory_name;
    }
}
