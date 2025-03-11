package com.fabiit.fabschoolapp.master.gradeMaster.repo;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fabiit.fabschoolapp.master.gradeMaster.entity.GradeEntity;
import com.fabiit.fabschoolapp.utils.FabEnum.state;

import jakarta.transaction.Transactional;

@Repository
public interface GradeRepo extends JpaRepository<GradeEntity, Integer> {

	List<GradeEntity> findAllByStateNot(state deleted);

	GradeEntity findByGradeId(int gradeId);

	@Modifying
	@Transactional
	@Query("update GradeEntity g set g.gradeName=:gradeName , g.userLastUpdated=:userLastUpdated , g.dtLastUpdated=:currentInstant where g.gradeId=:gradeId")
	int updateGrade(int gradeId, String gradeName, int userLastUpdated, Instant currentInstant);

	List<GradeEntity> findAllByState(state active);

	GradeEntity findByGradeName(String gradeName);

}
