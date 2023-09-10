package com.nick.product.manage.supplier;

import javax.persistence.*;

@Entity
@Table
public class Supplier {

    @Id
    private String supplier_uid;
    private String master_uid;
    private String supplier_name;
    private String supplier_email;
    private String supplier_password;
    private String supplier_mobile;
    private Integer supplier_customers;

    public Supplier() {

    }

    public String getMaster_uid() {
        return master_uid;
    }

    public void setMaster_uid(String master_uid) {
        this.master_uid = master_uid;
    }

    public String getSupplier_uid() {
        return supplier_uid;
    }

    public void setSupplier_uid(String supplier_uid) {
        this.supplier_uid = supplier_uid;
    }

    public String getSupplier_name() {
        return supplier_name;
    }

    public void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name;
    }

    public String getSupplier_email() {
        return supplier_email;
    }

    public void setSupplier_email(String supplier_email) {
        this.supplier_email = supplier_email;
    }

    public String getSupplier_password() {
        return supplier_password;
    }

    public void setSupplier_password(String supplier_password) {
        this.supplier_password = supplier_password;
    }

    public String getSupplier_mobile() {
        return supplier_mobile;
    }

    public void setSupplier_mobile(String supplier_mobile) {
        this.supplier_mobile = supplier_mobile;
    }

    public Integer getSupplier_customers() {
        return supplier_customers;
    }

    public void setSupplier_customers(Integer supplier_customers) {
        this.supplier_customers = supplier_customers;
    }

    public Supplier(String master_uid, String supplier_uid, String supplier_name, String supplier_email, String supplier_password, String supplier_mobile) {
        this.master_uid = master_uid;
        this.supplier_uid = supplier_uid;
        this.supplier_name = supplier_name;
        this.supplier_email = supplier_email;
        this.supplier_password = supplier_password;
        this.supplier_mobile = supplier_mobile;
    }

    public Supplier(String master_uid, String supplier_uid, String supplier_name, String supplier_email, String supplier_password, String supplier_mobile, Integer supplier_customers) {
        this.master_uid = master_uid;
        this.supplier_uid = supplier_uid;
        this.supplier_name = supplier_name;
        this.supplier_email = supplier_email;
        this.supplier_password = supplier_password;
        this.supplier_mobile = supplier_mobile;
        this.supplier_customers = supplier_customers;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "master_uid='" + master_uid + '\'' +
                ", supplier_uid='" + supplier_uid + '\'' +
                ", supplier_name='" + supplier_name + '\'' +
                ", supplier_email='" + supplier_email + '\'' +
                ", supplier_mobile='" + supplier_mobile + '\'' +
                ", supplier_customers=" + supplier_customers +
                '}';
    }
}
