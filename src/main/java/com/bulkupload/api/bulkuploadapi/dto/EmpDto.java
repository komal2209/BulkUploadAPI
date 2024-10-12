package com.bulkupload.api.bulkuploadapi.dto;

public class EmpDto {

    private String empName;
    private String empCode;

    private Integer empPhone;
    private String empEmail;

    private String empAddress;
    private String department;
    private String designation;

    public EmpDto() {
    }


    public EmpDto(String empName, String empCode, Integer empPhone, String empEmail, String empAddress, String department, String designation) {
        this.empName = empName;
        this.empCode = empCode;
        this.empPhone = empPhone;
        this.empEmail=empEmail;
        this.empAddress = empAddress;
        this.department = department;
        this.designation = designation;
    }



    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }
    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
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

    public String getDepartmentName() {
        return department;
    }

    public void setDepartmentName(String department) {
        this.department= department;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }


}
