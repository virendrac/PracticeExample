package com.kaluzny.web;

import com.kaluzny.domain.*;
import com.kaluzny.domain.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customers")
public class CustomerRestController {

    private CustomerRepository repository;

    @Inject
    public void setRepository(CustomerRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private ServiceCounterRestController serviceCounterRestController;

    @Autowired
    private TokenRestController tokenRestController;

    @RequestMapping(
            method = RequestMethod.POST)
    public ResponseEntity<?> addCustomer(@RequestBody Customer customer) {
        return new ResponseEntity<>(repository.save(customer), HttpStatus.CREATED);
    }

    @RequestMapping(
            method = RequestMethod.GET)
    public ResponseEntity<Collection<Customer>> getAllCustomers() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET)
    public ResponseEntity<Customer> getCustomerWithId(@PathVariable Long id) {
        return new ResponseEntity<>(repository.findOne(id), HttpStatus.OK);
    }

    @RequestMapping(
            params = {"name"},
            method = RequestMethod.GET)
    public ResponseEntity<Collection<Customer>> findCustomerWithName(@RequestParam(value = "name") String name) {
        return new ResponseEntity<>(repository.findByName(name), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.PUT)
    public ResponseEntity<Customer> updateCustomerFromDB(@PathVariable("id") long id, @RequestBody Customer customer) {

        Customer currentCustomer = repository.findOne(id);
        currentCustomer.setName(customer.getName());
        currentCustomer.setName(customer.getName());
        currentCustomer.setPhone(customer.getPhone());
        currentCustomer.setAddress(customer.getAddress());
        currentCustomer.setTypeOfService(customer.getTypeOfService());


        return new ResponseEntity<>(repository.save(currentCustomer), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE)
    public void deleteCustomerWithId(@PathVariable Long id) {
        repository.delete(id);
    }

    @RequestMapping(
            method = RequestMethod.DELETE)
    public void deleteAllCustomers() {
        repository.deleteAll();
    }


    @RequestMapping(
            path = "/display",
            method = RequestMethod.GET)
    public ResponseEntity<Map<Long, List<Long>>> displayCounterTokenList() {
        Map<Long, List<Long>> mapTokenCounter = new HashMap<>();
        List<ServiceCounter> counterlist= (List<ServiceCounter>) serviceCounterRestController.getAllServiceCounters().getBody();
        for(int i=0;i<counterlist.size();i++){
            List<Token> l=tokenRestController.getAllTokens(counterlist.get(i).getId());
            mapTokenCounter.put(counterlist.get(i).getId(),l.stream().map(t-> t.getId()).collect(Collectors.toList()));

        }

        return new ResponseEntity<>(mapTokenCounter, HttpStatus.OK);
    }
}
