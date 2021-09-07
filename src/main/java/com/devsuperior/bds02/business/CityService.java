package com.devsuperior.bds02.business;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.devsuperior.bds02.business.exceptions.DatabaseException;
import com.devsuperior.bds02.business.exceptions.ResourcecNotFoundException;
import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.repositories.CityRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository repository;
	
	public void delete(Long id) {
		City city = repository.findById(id).orElseThrow(() -> new ResourcecNotFoundException("Not found"));
		try {
			repository.delete(city);
		}catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}

	public List<CityDTO> findAll() {
		List<City> result = repository.findAll(Sort.by("name"));
		return result.stream().map(city -> new CityDTO(city)).collect(Collectors.toList());
	}

}
