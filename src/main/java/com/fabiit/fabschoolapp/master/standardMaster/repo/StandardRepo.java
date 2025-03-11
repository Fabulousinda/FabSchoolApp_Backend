package com.fabiit.fabschoolapp.master.standardMaster.repo;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fabiit.fabschoolapp.master.standardMaster.entity.StandardEntity;
import com.fabiit.fabschoolapp.utils.FabEnum.state;

import jakarta.transaction.Transactional;

@Repository
public interface StandardRepo extends JpaRepository<StandardEntity, Integer> {

	List<StandardEntity> findAllByStateNot(state deleted);

	List<StandardEntity> findAllByState(state active);

	StandardEntity findByStandardId(int standardId);
	List<StandardEntity> findByStandardIdIn(List<Integer> standardId);

	StandardEntity findByStandardIdAndState(int standardId, state state);

	@Modifying
	@Transactional
	@Query("update StandardEntity s set s.stdName=:stdName , s.userLastUpdated=:userLastUpdated , s.dtLastUpdated=:currentInstant where s.standardId=:standardId")
	int updateStandard(int standardId, String stdName, int userLastUpdated, Instant currentInstant);

	StandardEntity findByStdName(String standard);
	
	

}
