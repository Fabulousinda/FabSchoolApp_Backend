package com.fabiit.fabschoolapp.announcement.repo;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fabiit.fabschoolapp.announcement.entity.AnnouncementTypeEntity;
import com.fabiit.fabschoolapp.utils.FabEnum.state;

import jakarta.transaction.Transactional;

@Repository
public interface AnnouncementTypeRepo extends JpaRepository<AnnouncementTypeEntity, Integer> {

	AnnouncementTypeEntity findByAnnouncementTypeId(int announceId);

	@Modifying
	@Transactional
	@Query("update AnnouncementTypeEntity a set a.announcementType=:announceType , a.userLastUpdated=:announceUserLastUpdated , a.dtLastUpdated=:dtUserLastUpdated where a.announcementTypeId=:announceId")
	int updateAnnounce(int announceId, String announceType, int announceUserLastUpdated, Instant dtUserLastUpdated);

	List<AnnouncementTypeEntity> findAllByStateNot(state deleted);

}
