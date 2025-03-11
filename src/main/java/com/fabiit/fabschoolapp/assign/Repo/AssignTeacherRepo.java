package com.fabiit.fabschoolapp.assign.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fabiit.fabschoolapp.assign.Entity.AssignTeacherEntity;
import com.fabiit.fabschoolapp.assign.dto.TeacherListAssignDto;


@Repository
public interface AssignTeacherRepo extends JpaRepository<AssignTeacherEntity, Integer> {
	
	AssignTeacherEntity findByTeacherId(int teacherId);
	AssignTeacherEntity findByAssignId(int assignId);
	AssignTeacherEntity findByTeacherIdAndClassTeacherTrue(int teacherId);
	


	
	@Query("""
		    SELECT new com.fabiit.fabschoolapp.assign.dto.TeacherListAssignDto (
		        asg.teacherId,
		        asg.assignId,
		        COALESCE(st.standardId, 0),
		        asg.stdName,
		        COALESCE(d.divisionId, 0),
		        asg.divName,
		        COALESCE(subj.subjectId, 0),
		        asg.subName,
		        asg.classTeacher
		    ) 
		    FROM AssignTeacherEntity asg
		    LEFT JOIN StandardEntity st ON asg.stdName = st.stdName
		    LEFT JOIN DivisionEntity d ON asg.divName = d.divName
		    LEFT JOIN SubjectEntity subj ON asg.subName = subj.subName
		    WHERE asg.teacherId = :teacherId
		""")
		List<TeacherListAssignDto> findAssignedTeachers(@Param("teacherId") int teacherId);

	
}
