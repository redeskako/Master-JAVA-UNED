package com.uned.worldhealthbank.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uned.worldhealthbank.domain.Data;

@Repository
@Transactional
public interface DataRepository extends JpaRepository<Data, Long>{

	
	@Query("SELECT d FROM Data d where d.country.countryName LIKE :busqueda")
	public List<Data> dataSearch(@Param("busqueda") String busqueda);
	
	@Query("SELECT d FROM Data d where d.country.countryName LIKE :busqueda")
	public Page<Data> dataSearchPageable(@Param("busqueda") String busqueda, Pageable pageable );
}
