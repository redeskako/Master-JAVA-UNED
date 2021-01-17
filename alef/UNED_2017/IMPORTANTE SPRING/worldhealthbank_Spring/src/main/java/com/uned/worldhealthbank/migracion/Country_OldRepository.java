package com.uned.worldhealthbank.migracion;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
@Transactional
public interface Country_OldRepository extends JpaRepository<Country_Old, String>{

}
