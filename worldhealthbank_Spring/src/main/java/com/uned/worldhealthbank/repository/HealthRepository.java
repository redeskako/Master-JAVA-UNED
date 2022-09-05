package com.uned.worldhealthbank.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uned.worldhealthbank.domain.Country;
import com.uned.worldhealthbank.domain.Health;

@Repository
@Transactional
public interface HealthRepository extends JpaRepository<Health, Long>{

	public Health findByIndicadorCode(String indicadorCode);
}
