package com.bulkupload.api.bulkuploadapi.entity;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Designation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer desiId;
    private String  designationName;

    public Designation() {
    }

    public Designation(Integer desiId, String designationName) {
        this.desiId = desiId;
        this.designationName = designationName;
    }

    public String getDesignation()
    {
        return designationName;
    }

    public void setDesignation(String designationName) {
        this.designationName = designationName;
    }

    public Integer getDesiId() {
        return desiId;
    }

    public void setDesiId(Integer desiId) {
        this.desiId = desiId;
    }

}
