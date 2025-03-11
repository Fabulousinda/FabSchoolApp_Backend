package com.fabiit.fabschoolapp.master.studentMaster.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fabiit.fabschoolapp.master.studentMaster.Entity.StudentFourthFormEntity;

@Repository
public interface StudentFourthFormRepo extends JpaRepository<StudentFourthFormEntity, Integer> {

}
