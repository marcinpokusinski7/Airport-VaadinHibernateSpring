package com.vaadin.fastui.backend.service;


import com.vaadin.fastui.backend.entity.Flight;
import com.vaadin.fastui.backend.repository.FlightRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@Data
@NoArgsConstructor
public class FlightService {
    private static final Logger LOGGER = Logger.getLogger(FlightService.class.getName());
    private FlightRepository flightRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;

    }

    public List<Flight> findAll(){
        return flightRepository.findAll();
    }

    public List<Flight> findAll(String filterText){
        if(filterText == null || filterText.isEmpty()){
            return flightRepository.findAll();
        } else{
            return flightRepository.search(filterText);
        }

    }
    public Flight findById(int id){
        return flightRepository.getOne(id);
    }

    public long count(){
        return flightRepository.count();
    }

    public void delete(Flight flight){
        flightRepository.delete(flight);
    }

    public void save(Flight flight){
        if(flight == null){
            LOGGER.log(Level.SEVERE,
                    "Flights are null");
            return;
        }
        flightRepository.save(flight);
    }

}
