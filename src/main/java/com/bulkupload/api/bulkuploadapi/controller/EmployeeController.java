package com.bulkupload.api.bulkuploadapi.controller;

import com.bulkupload.api.bulkuploadapi.dto.EmpDto;
import com.bulkupload.api.bulkuploadapi.helper.EmployeeHelper;
import com.bulkupload.api.bulkuploadapi.service.EmployeeService;
import org.apache.poi.openxml4j.util.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @PostMapping("/Employee/upload")
    public ResponseEntity<?> upload(@RequestParam("file")MultipartFile file)
    {
        if(EmployeeHelper.checkExcelFormat(file))
        {
            // true
            this.employeeService.save(file);

            return ResponseEntity.ok(Map.of("message","file upload successfully"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel file.");
    }


    @GetMapping("/Employee/getAllEmployee")
    public List<EmpDto> getAllEmployee()
    {
        return employeeService.getAllEmployeeData();

    }

    @GetMapping("/Employee/getFilterData")
    public List<EmpDto> getFilterData(
                                        @RequestParam(required = false, defaultValue = "0") Integer designationId,
                                        @RequestParam(required = false, defaultValue = "0") Integer departmentId,
                                        @RequestParam(required = false, defaultValue = "") String searchText)
    {

        return employeeService.getEmployeeFilterData(designationId,departmentId,searchText);
    }



}
