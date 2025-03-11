package com.fabiit.fabschoolapp.master.divisionMaster.repo;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fabiit.fabschoolapp.master.divisionMaster.entity.DivisionEntity;
import com.fabiit.fabschoolapp.utils.FabEnum.state;

import jakarta.transaction.Transactional;

@Repository
public interface DivisionRepo extends JpaRepository<DivisionEntity, Integer> {

	List<DivisionEntity> findAllByStateNot(state deleted);

	DivisionEntity findByDivisionId(int divisionId);

	@Modifying
	@Transactional
	@Query("update DivisionEntity d set d.divName=:divName , d.userLastUpdated=:userLastUpdated , d.dtLastUpdated=:currentInstant where d.divisionId=:divisionId")
	int updateDivision(int divisionId, String divName, int userLastUpdated, Instant currentInstant);

	List<DivisionEntity> findAllByState(state active);

	List<DivisionEntity> findAllByDivisionIdInAndState(List<Integer> division, state active);

	DivisionEntity findByDivisionId(Integer div);

	
	


}
