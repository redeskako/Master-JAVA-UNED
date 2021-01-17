package com.uned.worldhealthbank.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uned.worldhealthbank.domain.Country;


@Repository
@Transactional
public interface CountryRepository extends JpaRepository<Country, Long>{

	public Country findByCountryCode(String countryCode);
}
