package com.uned.worldhealthbank.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uned.worldhealthbank.domain.Role_User;


@Repository
@Transactional
public interface Rol_UserRepository extends JpaRepository<Role_User, Long>{

}

