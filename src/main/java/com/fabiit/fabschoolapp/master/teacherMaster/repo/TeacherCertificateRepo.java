package com.fabiit.fabschoolapp.master.teacherMaster.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fabiit.fabschoolapp.master.teacherMaster.Entity.TeacherCertificate;
@Repository
public interface TeacherCertificateRepo extends JpaRepository<TeacherCertificate, Integer> {

}
