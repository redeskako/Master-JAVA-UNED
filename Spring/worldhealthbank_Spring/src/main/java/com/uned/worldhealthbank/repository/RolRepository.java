package com.uned.worldhealthbank.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uned.worldhealthbank.domain.Role;

@Repository
@Transactional
public interface RolRepository extends JpaRepository<Role, Long>{

}
