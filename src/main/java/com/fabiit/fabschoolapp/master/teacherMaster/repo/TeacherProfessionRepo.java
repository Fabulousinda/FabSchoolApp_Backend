package com.fabiit.fabschoolapp.master.teacherMaster.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fabiit.fabschoolapp.master.teacherMaster.Entity.TeacherProfessionEntity;
import com.fabiit.fabschoolapp.master.teacherMaster.Entity.TeacherMasterEntity;
@Repository
public interface TeacherProfessionRepo extends JpaRepository<TeacherProfessionEntity, Integer> {

	

}
