package com.fabiit.fabschoolapp.master.teacherMaster.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fabiit.fabschoolapp.master.teacherMaster.Entity.TeacherThirdFormEntity;
@Repository
public interface TeacherThirdFormRepo extends JpaRepository<TeacherThirdFormEntity, Integer> {

}
