package com.bulkupload.api.bulkuploadapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer empId;

    private String empCode;
    private String empName;
    private Integer empPhone;
    private String empEmail;
    private String empAddress;
    private Integer departmentId;
    private Integer designationId;

    public Employee() {
    }

    public Employee(Integer empId, String empCode, String empName, Integer empPhone, String empEmail, String empAddress, Integer departmentId,Integer designationId) {
        this.empId = empId;
        this.empCode = empCode;
        this.empName = empName;
        this.empPhone = empPhone;
        this.empEmail=empEmail;
        this.empAddress = empAddress;
        this.departmentId = departmentId;
        this.designationId = designationId;
    }



    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Integer getEmpPhone() {
        return empPhone;
    }

    public void setEmpPhone(Integer empPhone) {
        this.empPhone = empPhone;
    }


    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    public String getEmpAddress() {
        return empAddress;
    }

    public void setEmpAddress(String empAddress) {
        this.empAddress = empAddress;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId= departmentId;
    }


    public Integer getDesignationId() {
        return designationId;
    }

    public void setDesignationId(Integer designationId) {
        this.designationId = designationId;
    }
}
