package com.uned.worldhealthbank.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uned.worldhealthbank.domain.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByLogin(String login);

}
