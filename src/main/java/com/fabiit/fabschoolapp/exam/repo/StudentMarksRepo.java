package com.fabiit.fabschoolapp.exam.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fabiit.fabschoolapp.exam.Dto.AddMarksDto;
import com.fabiit.fabschoolapp.exam.entity.ExamActivationEntity;
import com.fabiit.fabschoolapp.exam.entity.StudentMarksEntity;
@Repository
public interface StudentMarksRepo extends JpaRepository<StudentMarksEntity, Integer> {

//	@Query(value = """
//		  	SELECT 
//    asg.studentId,
//    sd.name AS student_name,
//    ea.totalMarks,
//    sm.studentMarks,
//    ea.activationId,
//    ea.evaluationType,
//    sm.marksId
//  
//FROM AssignStudentEntity asg
//LEFT JOIN StudentMasterEntity sd ON asg.studentId = sd.studentId
//LEFT JOIN ExamActivationEntity ea 
//    ON ea.subject = (SELECT subName FROM SubjectEntity WHERE subjectId = :subject)
//    AND ea.examId = :examId
//    AND ea.standard= (SELECT stdName FROM StandardEntity WHERE standardId = :std)
//    
//LEFT JOIN StudentMarksEntity sm 
//    ON sm.activationId = ea.activationId 
//    AND sm.studentId = asg.studentId
//WHERE asg.stdName = (SELECT stdName FROM StandardEntity WHERE standardId = :std)
//AND asg.divName = 
//(SELECT divName FROM DivisionEntity WHERE divisionId = :division
//	
// )""")
//	List<Object[]> findStudentMarks(
//		     int examId,
//		     int std,
//		     int division,
//		     int subject
//		);		
	
	@Query("""
		    SELECT new com.fabiit.fabschoolapp.exam.Dto.AddMarksDto(
		        asg.studentId, 
		        sd.name, 
		        ea.totalMarks, 
		        COALESCE(sm.studentMarks, -1), 
		        ea.activationId, 
		        ea.evaluationType, 
		        sm.studentGrade,
		        COALESCE(sm.marksId, 0)
		    ) 
		    FROM AssignStudentEntity asg
		    LEFT JOIN StudentMasterEntity sd ON asg.studentId = sd.studentId
		    LEFT JOIN ExamActivationEntity ea 
		        ON ea.subject = (SELECT sub.subName FROM SubjectEntity sub WHERE sub.subjectId = :subject)
		        AND ea.examId = :examId
		        AND ea.standard = (SELECT std.stdName FROM StandardEntity std WHERE std.standardId = :std)
		    LEFT JOIN StudentMarksEntity sm 
		        ON sm.activationId = ea.activationId 
		        AND sm.studentId = asg.studentId
		    WHERE asg.stdName = (SELECT std.stdName FROM StandardEntity std WHERE std.standardId = :std)
		    AND asg.divName = (SELECT div.divName FROM DivisionEntity div WHERE div.divisionId = :division)
		""")
		List<AddMarksDto> findStudentMarks(
		    @Param("examId") int examId,
		    @Param("std") int std,
		    @Param("division") int division,
		    @Param("subject") int subject
		);


	@Query("""
		    SELECT new com.fabiit.fabschoolapp.exam.Dto.AddMarksDto( asg.studentId, sd.name, ea.totalMarks, sm.studentMarks, 
		           ea.activationId, ea.evaluationType,sm.studentGrade, sm.marksId )
		    FROM AssignStudentEntity asg
		    LEFT JOIN StudentMasterEntity sd ON asg.studentId = sd.studentId
		    LEFT JOIN ExamActivationEntity ea 
		        ON ea.subject = (SELECT s.subName FROM SubjectEntity s WHERE s.subjectId = :subject)
		        AND ea.examId = :examId
		        AND ea.standard = (SELECT st.stdName FROM StandardEntity st WHERE st.standardId = :std)
		    LEFT JOIN StudentMarksEntity sm 
		        ON sm.activationId = ea.activationId 
		        AND sm.studentId = asg.studentId
		    WHERE asg.stdName = (SELECT st.stdName FROM StandardEntity st WHERE st.standardId = :std)
		    AND asg.divName = (SELECT d.divName FROM DivisionEntity d WHERE d.divisionId = :division)
		    AND (
		        LOWER(sd.name) LIKE LOWER(CONCAT('%', :searchKeyword, '%')) 
		        OR CAST(asg.studentId AS string) LIKE CONCAT('%', :searchKeyword, '%')
		        OR CAST(ea.totalMarks AS string) LIKE CONCAT('%', :searchKeyword, '%')
		        OR CAST(sm.studentMarks AS string) LIKE CONCAT('%', :searchKeyword, '%')
		    )
		    """)
		List<AddMarksDto> findStudentMarksWithSearch(
		    @Param("examId") int examId,
		    @Param("std") int stdId,
		    @Param("division") int divId,
		    @Param("subject") int subId,
		    @Param("searchKeyword") String searchKeyword
		);

//		@Query("SELECT e FROM ExamActivationEntity e WHERE " +
//	            "LOWER(e.standard) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
//	            "LOWER(e.subject) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
//	            "STR(e.totalMarks) LIKE CONCAT('%', :keyword, '%') OR " +
//	            "STR(e.passingMarks) LIKE CONCAT('%', :keyword, '%')")
//	    List<ExamActivationEntity> searchByKeyword(@Param("keyword") String keyword);

		    



	
}
