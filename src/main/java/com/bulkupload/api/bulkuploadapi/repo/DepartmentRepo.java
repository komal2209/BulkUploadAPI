package com.bulkupload.api.bulkuploadapi.repo;

import com.bulkupload.api.bulkuploadapi.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepo extends JpaRepository<Department,Integer>{

    Department findByDepartmentName(String departmentName);

    @Query("Select departmentName from Department dp where dp.departId= :departId")
    String findDepartmentNameById(@Param("departId") Integer Id);
}



