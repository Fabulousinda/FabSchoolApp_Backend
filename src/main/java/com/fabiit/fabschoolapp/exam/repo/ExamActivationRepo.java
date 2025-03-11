package com.fabiit.fabschoolapp.exam.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fabiit.fabschoolapp.exam.entity.ExamActivationEntity;
@Repository
public interface ExamActivationRepo extends JpaRepository<ExamActivationEntity, Integer> {

	ExamActivationEntity findByActivationId(int activationId);

//	@Query("SELECT e FROM ExamActivationEntiy e WHERE " +
//            "e.standard LIKE %:keyword% OR " +
//            "e.subject LIKE %:keyword% OR " +
//            "CAST(e.totalMarks AS string) LIKE %:keyword% OR " +
//            "CAST(e.passingMarks AS string) LIKE %:keyword%")
//    List<ExamActivationEntity> searchByKeyword(@Param("keyword") String keyword);
	
	@Query("SELECT e FROM ExamActivationEntity e WHERE " +
            "LOWER(e.standard) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(e.subject) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "STR(e.totalMarks) LIKE CONCAT('%', :keyword, '%') OR " +
            "STR(e.passingMarks) LIKE CONCAT('%', :keyword, '%')")
    List<ExamActivationEntity> searchByKeyword(@Param("keyword") String keyword);

	ExamActivationEntity findByExamIdAndStandardAndSubject(int examId, String standard, String subjectName);

	List<ExamActivationEntity> findByExamId(int examId);
	
	
	

}
