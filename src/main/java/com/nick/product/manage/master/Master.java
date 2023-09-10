package com.nick.product.manage.master;

import javax.persistence.*;

@Entity
@Table
public class Master {


    @Id
    private String master_uid;
    private String master_name;
    private String master_email;
    private String master_mobile;
    private String master_password;
    private Integer master_suppliers;

    public Master() {

    }


    public String getMaster_password() {
        return master_password;
    }

    public void setMaster_password(String master_password) {
        this.master_password = master_password;
    }

    public String getMaster_uid() {
        return master_uid;
    }

    public void setMaster_uid(String master_uid) {
        this.master_uid = master_uid;
    }

    public String getMaster_name() {
        return master_name;
    }

    public void setMaster_name(String master_name) {
        this.master_name = master_name;
    }

    public String getMaster_email() {
        return master_email;
    }

    public void setMaster_email(String master_email) {
        this.master_email = master_email;
    }

    public String getMaster_mobile() {
        return master_mobile;
    }

    public void setMaster_mobile(String master_mobile) {
        this.master_mobile = master_mobile;
    }

    public Integer getMaster_suppliers() {
        return master_suppliers;
    }

    public void setMaster_suppliers(Integer master_suppliers) {
        this.master_suppliers = master_suppliers;
    }

    public Master(String master_uid, String master_name, String master_email, String master_mobile, String master_password, Integer master_suppliers) {
        this.master_uid = master_uid;
        this.master_name = master_name;
        this.master_email = master_email;
        this.master_mobile = master_mobile;
        this.master_password = master_password;
        this.master_suppliers = master_suppliers;
    }

    public Master(String master_uid, String master_name, String master_email, String master_mobile, String master_password) {
        this.master_uid = master_uid;
        this.master_name = master_name;
        this.master_email = master_email;
        this.master_mobile = master_mobile;
        this.master_password = master_password;
    }

    @Override
    public String toString() {
        return "Master{" +
                "master_uid='" + master_uid + '\'' +
                ", master_name='" + master_name + '\'' +
                ", master_email='" + master_email + '\'' +
                ", master_mobile='" + master_mobile + '\'' +
                ", master_suppliers=" + master_suppliers +
                '}';
    }
}
