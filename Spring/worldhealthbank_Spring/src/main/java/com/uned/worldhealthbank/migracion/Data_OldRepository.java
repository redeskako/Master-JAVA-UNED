package com.uned.worldhealthbank.migracion;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface Data_OldRepository extends JpaRepository<Data_Old, Data_Old_Id>{

}
