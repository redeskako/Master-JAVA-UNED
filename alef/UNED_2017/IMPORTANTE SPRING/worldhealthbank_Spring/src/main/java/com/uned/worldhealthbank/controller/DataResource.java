package com.uned.worldhealthbank.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uned.worldhealthbank.domain.Country;
import com.uned.worldhealthbank.domain.Data;
import com.uned.worldhealthbank.migracion.Data_OldRepository;
import com.uned.worldhealthbank.repository.CountryRepository;
import com.uned.worldhealthbank.repository.DataRepository;
import com.uned.worldhealthbank.repository.RolRepository;
import javax.inject.Inject;

import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/worldhealthbank")
public class DataResource {

	
	@Inject
	private DataRepository dataRepository; 
	
	
	@Inject
	private RolRepository roleRepository; 
	
	@Inject
	private CountryRepository countryRepository;

	@Inject
	private Data_OldRepository data_OldRepository;
	
	
	/*
	 * GET All datas
	 */
	
	@RequestMapping(value = "/datas",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Data> getAllData() {
		
		return dataRepository.findAll();
	}
	
	/*
	 * GET Pageable Datas
	 */
	@RequestMapping(value = "/datas/pag/{pag}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<Data> getPageableData(@PathVariable int pag) {
		
		
		return dataRepository.findAll(new PageRequest(pag, 20));
	}

    
    /*
	 * GET Search1 datas
	 */
	
    @RequestMapping(value = "/datasearch/{busqueda}/pag/{pag}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<Data> getDataSearch(@PathVariable String busqueda, @PathVariable int pag){
    	
    	Pageable pageRequest = new PageRequest(pag, 20); 
    	
		if(busqueda.equals(" ")) {
			Page<Data> page = dataRepository.findAll(pageRequest);
			return dataRepository.findAll(pageRequest); //http://www.localhost:8080/worldhealthbank/datasearch/%20/pag/0	 
		}
		else
			return dataRepository.dataSearchPageable(busqueda+"%", pageRequest);
	}

	

	
	/*
	 * GET one data
	 */
	
	@RequestMapping(value = "/data/get/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Data getOneData(@PathVariable Long id) {
		
		return dataRepository.findOne(id);
	}
	
	
	/*
	 * DELETE one data
	 */
	@RequestMapping(value = "/data/del/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void getDelete(@PathVariable Long id) {
		dataRepository.delete(id);
	}
	


    /**
     * POST  /data :Crear nuevo Data
     *
     * @param data
     */
    @RequestMapping(value = "/data/saveData/",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void createData(@RequestBody Data data) throws URISyntaxException {
      dataRepository.save(data);
    }
    
}
