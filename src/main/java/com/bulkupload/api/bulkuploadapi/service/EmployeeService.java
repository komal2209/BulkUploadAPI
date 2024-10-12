package com.bulkupload.api.bulkuploadapi.service;

import com.bulkupload.api.bulkuploadapi.dto.EmpDto;
import com.bulkupload.api.bulkuploadapi.dto.EmpProjection;
import com.bulkupload.api.bulkuploadapi.entity.Department;
import com.bulkupload.api.bulkuploadapi.entity.Designation;
import com.bulkupload.api.bulkuploadapi.entity.Employee;
import com.bulkupload.api.bulkuploadapi.helper.EmployeeHelper;
import com.bulkupload.api.bulkuploadapi.repo.DepartmentRepo;
import com.bulkupload.api.bulkuploadapi.repo.DesignationRepo;
import com.bulkupload.api.bulkuploadapi.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeService
{
    @Autowired
    private EmployeeRepo eRepo;
    @Autowired
    private DepartmentRepo dRepo;
    @Autowired
    private DesignationRepo desiRepo;
    public void save(MultipartFile file)
    {
        try
        {
           List<EmpDto> list= EmployeeHelper.convertExcelToListOfProduct(file.getInputStream());
           List<Department> departList=new ArrayList<>();
           List<Designation> desigList=new ArrayList<>();
            List<Employee>  empList=new ArrayList<>();

           for(EmpDto item :list) {
               Department existingDepartment = dRepo.findByDepartmentName(item.getDepartmentName());

               if (existingDepartment == null) {
                   Department newDepart = new Department();
                   newDepart.setDepartmentName(item.getDepartmentName());
                   departList.add(newDepart);
               }
           }
            if(!departList.isEmpty())
            {
                dRepo.saveAll(departList);
            }
            for(EmpDto item :list) {
               Designation existingDes = desiRepo.findByDesignationName(item.getDesignation());

               if(existingDes == null)
               {
                   Designation newDes=new Designation();
                   newDes.setDesignation(item.getDesignation());
                   desigList.add(newDes);
               }

           }
            if(!desigList.isEmpty())
            {
                desiRepo.saveAll(desigList);
            }

            for(EmpDto items :list)
            {
                Department existingDepart = dRepo.findByDepartmentName(items.getDepartmentName());
                Designation existingDesi = desiRepo.findByDesignationName(items.getDesignation());
                Employee emp=eRepo.findByEmpEmail(items.getEmpEmail());
                if(existingDepart !=null && existingDesi !=null )
                {
                    if(emp== null)
                    {
                        int departId = existingDepart.getDepartId();
                        int desiId=existingDesi.getDesiId();
                        Employee newEmp=new Employee();
                        newEmp.setEmpName(items.getEmpName());
                        newEmp.setEmpCode(items.getEmpCode());
                        newEmp.setEmpPhone(items.getEmpPhone());
                        newEmp.setEmpEmail(items.getEmpEmail());
                        newEmp.setEmpAddress(items.getEmpAddress());
                        newEmp.setDepartmentId(departId);
                        newEmp.setDesignationId(desiId);
                        empList.add(newEmp);
                    }
                     else
                     {
                        int departId = existingDepart.getDepartId();
                        int desiId=existingDesi.getDesiId();
                         emp.setEmpName(items.getEmpName());
                         emp.setEmpCode(items.getEmpCode());
                         emp.setEmpPhone(items.getEmpPhone());
                         emp.setEmpAddress(items.getEmpAddress());
                         emp.setDepartmentId(departId);
                         emp.setDesignationId(desiId);
                        empList.add(emp);
                    }


                }

            }
            if(!empList.isEmpty())
            {
                eRepo.saveAll(empList);
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }


  /*  public List<EmpDto> getAllEmployeeData()
    {
        EmpDto emp=new EmpDto();
        List<EmpDto> list=new ArrayList<>();

        List<Employee> empData=eRepo.findAll();
       for(Employee item:empData)
       {
           emp.setEmpName(item.getEmpName());
           emp.setEmpCode(item.getEmpCode());
           emp.setEmpPhone(item.getEmpPhone());
           emp.setEmpEmail(item.getEmpEmail());
           emp.setEmpAddress(item.getEmpAddress());
           emp.setDepartmentName((dRepo.findDepartmentNameById(item.getDepartmentId())));

          // emp.setDepartmentName((dRepo.findById(item.getDepartmentId()).toString()));

           emp.setDesignation((desiRepo.findDesignationNameById(item.getDesignationId())));

           list.add(emp);
       }
return list;

    }
*/




  public List<EmpDto> getAllEmployeeData() {
        List<Employee> empData = eRepo.findAll();

        Map<Integer, String> departmentMap = empData.stream()
                .map(Employee::getDepartmentId)
                .distinct()
                .collect(Collectors.toMap(
                        deptId -> deptId,
                        deptId -> dRepo.findDepartmentNameById(deptId)
                ));

        Map<Integer, String> designationMap = empData.stream()
                .map(Employee::getDesignationId)
                .distinct()
                .collect(Collectors.toMap(
                        desiId -> desiId,
                        desiId -> desiRepo.findDesignationNameById(desiId)
                ));

        return empData.stream().map(item -> {
            EmpDto emp = new EmpDto();
            emp.setEmpName(item.getEmpName());
            emp.setEmpCode(item.getEmpCode());
            emp.setEmpPhone(item.getEmpPhone());
            emp.setEmpEmail(item.getEmpEmail());
            emp.setEmpAddress(item.getEmpAddress());

            emp.setDepartmentName(departmentMap.get(item.getDepartmentId()));
            emp.setDesignation(designationMap.get(item.getDesignationId()));

            return emp;
        }).collect(Collectors.toList());
    }


@Transactional
    public List<EmpDto> getEmployeeFilterData(Integer designationId, Integer departmentId,String searchText) {

      List<EmpProjection> empProjectionList= eRepo.filterEmployees(designationId, departmentId, searchText);
      return empProjectionList.stream().map(p->new EmpDto(
              p.getEmpName(),
              p.getEmpCode(),
              p.getEmpPhone(),
              p.getEmpEmail(),
              p.getEmpAddress(),
              p.getDepartment(),
              p.getDesignation()
              )
          ).collect(Collectors.toList());
    }
}
