package com.fabiit.fabschoolapp.master.teacherMaster.repo;

import java.awt.print.Pageable;
import java.util.List;

import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fabiit.fabschoolapp.master.teacherMaster.Dto.TeacherListDto;
import com.fabiit.fabschoolapp.master.teacherMaster.Entity.TeacherMasterEntity;
import com.fabiit.fabschoolapp.master.teacherMaster.Entity.TeacherThirdFormEntity;
@Repository
public interface TeacherMasterRepo extends JpaRepository<TeacherMasterEntity, Integer> {

//	TeacherMasterEntity findByTeacherId(Integer secondId);

	TeacherMasterEntity deleteByTeacherId(Integer secondId);

	TeacherMasterEntity findAllByTeacherId(Integer secondId);

	@Query("""
		    SELECT new com.fabiit.fabschoolapp.master.teacherMaster.Dto.TeacherListDto (
		        t.teacherId, 
		        t.name,
		        t.phone,
		        t.emailAddress
		    ) 
		    FROM TeacherMasterEntity t
		    
		""")
		List<TeacherListDto> findTeachers();




	

	
	
}
