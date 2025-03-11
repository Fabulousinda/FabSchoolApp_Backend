package com.fabiit.fabschoolapp.master.studentMaster.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fabiit.fabschoolapp.master.studentMaster.Entity.StudentMasterEntity;
import com.fabiit.fabschoolapp.master.studentMaster.Entity.StudentSecondFormEntity;
@Repository
public interface StudentMasterRepo extends JpaRepository<StudentMasterEntity, Integer>{

	

	StudentMasterEntity findAllByStudentId(Integer id);

	StudentMasterEntity findByName(String name);
	
	List<StudentMasterEntity> findByFatherStartsWith(String father);
	
    List<StudentMasterEntity> findByNameLike(String name);
    
    List<StudentMasterEntity> findByMotherStartsWith(String mother);
    
    
    
    List<StudentMasterEntity> findByNameStartsWith(String name);
    
    
    
 
    
    List<StudentMasterEntity> findByNameStartsWithOrFatherStartsWithOrMotherStartsWith(String name, String fatherName, String motherName);

    
    


	

}
