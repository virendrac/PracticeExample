package com.kaluzny.web;

import com.kaluzny.domain.ServiceCounter;
import com.kaluzny.domain.ServiceCounterRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Collection;

@RestController
@RequestMapping("/api/serviceCounters")
public class ServiceCounterRestController {

    private ServiceCounterRepository repository;

    @Inject
    public void setRepository(ServiceCounterRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(
            method = RequestMethod.POST)
    public ResponseEntity<?> addServiceCounter(@RequestBody ServiceCounter serviceCounter) {
        return new ResponseEntity<>(repository.save(serviceCounter), HttpStatus.CREATED);
    }

    @RequestMapping(
            method = RequestMethod.GET)
    public ResponseEntity<Collection<ServiceCounter>> getAllServiceCounters() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Collection<ServiceCounter>> getAllServiceCounters(String counterType) {
        return new ResponseEntity<>(repository.findByCounterType(counterType), HttpStatus.OK);
    }


    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET)
    public ResponseEntity<ServiceCounter> getServiceCounterWithId(@PathVariable Long id) {
        return new ResponseEntity<>(repository.findOne(id), HttpStatus.OK);
    }


    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.PUT)
    public ResponseEntity<ServiceCounter> updateServiceCounterFromDB(@PathVariable("id") long id, @RequestBody ServiceCounter serviceCounter) {

        ServiceCounter currentServiceCounter = repository.findOne(id);
        currentServiceCounter.setId(serviceCounter.getId());
        currentServiceCounter.setCounterType(serviceCounter.getCounterType());



        return new ResponseEntity<>(repository.save(currentServiceCounter), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE)
    public void deleteServiceCounterWithId(@PathVariable Long id) {
        repository.delete(id);
    }

    @RequestMapping(
            method = RequestMethod.DELETE)
    public void deleteAllServiceCounters() {
        repository.deleteAll();
    }



}
