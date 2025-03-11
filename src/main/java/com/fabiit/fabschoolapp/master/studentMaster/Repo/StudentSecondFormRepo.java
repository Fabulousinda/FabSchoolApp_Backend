package com.fabiit.fabschoolapp.master.studentMaster.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fabiit.fabschoolapp.master.studentMaster.Entity.StudentSecondFormEntity;
@Repository
public interface StudentSecondFormRepo extends JpaRepository<StudentSecondFormEntity, Integer> {

}
