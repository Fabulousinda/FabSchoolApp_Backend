package com.fabiit.fabschoolapp.master.subjectMaster.repo;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fabiit.fabschoolapp.master.subjectMaster.entity.SubjectEntity;
import com.fabiit.fabschoolapp.utils.FabEnum.state;

import jakarta.transaction.Transactional;

@Repository
public interface SubjectRepo extends JpaRepository<SubjectEntity, Integer> {

	List<SubjectEntity> findAllByStateNot(state deleted);

	List<SubjectEntity> findAllByState(state active);

	SubjectEntity findBySubjectId(int subjectId);

	SubjectEntity findBySubjectIdAndState(int subjectId, state state);

	@Modifying
	@Transactional
	@Query("update SubjectEntity s set s.subName=:subName , s.userLastUpdated=:userLastUpdated , s.dtLastUpdated=:currentInstant where s.subjectId=:subjectId")
	int updateSubject(int subjectId, String subName, int userLastUpdated, Instant currentInstant);

	List<SubjectEntity> findAllBySubjectIdInAndState(List<Integer> division, state active);

	SubjectEntity findBySubjectId(Integer sub);

	SubjectEntity findBySubName(String subject);

}
