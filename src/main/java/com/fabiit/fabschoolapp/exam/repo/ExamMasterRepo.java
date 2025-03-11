package com.fabiit.fabschoolapp.exam.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fabiit.fabschoolapp.exam.entity.ExamMasterEntity;
import com.fabiit.fabschoolapp.utils.FabEnum.state;
@Repository

public interface ExamMasterRepo extends JpaRepository<ExamMasterEntity, Integer>{

	ExamMasterEntity findByExamId(int examId);

	ExamMasterEntity findByExamIdAndState(int examId, state active);

}
