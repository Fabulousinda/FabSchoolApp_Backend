package com.fabiit.fabschoolapp.jwt.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fabiit.fabschoolapp.jwt.entity.Role;
import com.fabiit.fabschoolapp.jwt.utils.ERole;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}