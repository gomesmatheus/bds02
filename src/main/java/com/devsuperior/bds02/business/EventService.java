package com.devsuperior.bds02.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.bds02.business.exceptions.ResourcecNotFoundException;
import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.repositories.CityRepository;
import com.devsuperior.bds02.repositories.EventRepository;

@Service
public class EventService {

	@Autowired
	private EventRepository repository;
	
	@Autowired
	private CityRepository cityRepository;
	
	public EventDTO update(Long id, EventDTO dto) {
		Event event = repository.findById(id).orElseThrow(() -> new ResourcecNotFoundException("Not found"));
		event.setName(dto.getName());
		event.setUrl(dto.getUrl());
		event.setDate(dto.getDate());
		City city = cityRepository.findById(dto.getCityId()).orElse(null);
		event.setCity(city);
		
		return new EventDTO(repository.save(event));
	}

}
