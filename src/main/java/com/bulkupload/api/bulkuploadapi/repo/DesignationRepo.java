package com.bulkupload.api.bulkuploadapi.repo;

import com.bulkupload.api.bulkuploadapi.entity.Designation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignationRepo extends JpaRepository<Designation, Integer> {

    Designation findByDesignationName(String designationName);

    @Query("Select designationName from Designation dg where dg.desiId=:desiId")
    String findDesignationNameById(@Param("desiId") Integer Id);

}
