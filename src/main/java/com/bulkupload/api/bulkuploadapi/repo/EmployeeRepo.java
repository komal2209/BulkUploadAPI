package com.bulkupload.api.bulkuploadapi.repo;

import com.bulkupload.api.bulkuploadapi.dto.EmpDto;
import com.bulkupload.api.bulkuploadapi.dto.EmpProjection;
import com.bulkupload.api.bulkuploadapi.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer>
{
    @Override
    List<Employee> findAll();

    @Transactional(readOnly = true)
   @Procedure(procedureName = "GetFilterData")
 //   @Query(value = "CALL GetFilterData(:designationId, :departmentId, :searchText)", nativeQuery = true)

    List<EmpProjection> filterEmployees(@Param("designationId") Integer designationId,
                                        @Param("departmentId") Integer departmentId,
                                        @Param("searchText") String searchText);


    Employee findByEmpEmail(String empEmail);
}
