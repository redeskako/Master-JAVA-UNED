package com.uned.worldhealthbank.migracion;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface Health_OldRepository extends JpaRepository<Health_Old, String>{

}
