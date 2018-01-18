package com.kaluzny.web;

import com.kaluzny.domain.Customer;
import com.kaluzny.domain.ServiceCounter;
import com.kaluzny.domain.Token;
import com.kaluzny.domain.TokenRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.*;

@RestController
@RequestMapping("/api/tokens")
public class TokenRestController {

    private TokenRepository repository;

    @Inject
    public void setRepository(TokenRepository repository) {
        this.repository = repository;
    }
    @Autowired
    private ServiceCounterRestController serviceCounterRestController;
    @Autowired
    private CustomerRestController customerRestController;

    @RequestMapping(
            method = RequestMethod.POST)
    public ResponseEntity<?> addToken(@RequestBody Token token) {
        Customer customer=customerRestController.getCustomerWithId(token.getCustomerId()).getBody();
        if(customer !=  null){
            List<ServiceCounter> serviceCounters = (List<ServiceCounter>) serviceCounterRestController.getAllServiceCounters(customer.getTypeOfService()).getBody();
            int tokens=repository.findByServiceCounterId(serviceCounters.get(0).getId()).size();
            int index=0;
            for(int i=1;i<serviceCounters.size();i++) {

                if(tokens > repository.findByServiceCounterId(serviceCounters.get(i).getId()).size()){
                    tokens= repository.findByServiceCounterId(serviceCounters.get(i).getId()).size();
                    index=i;
                }
            }
            token.setTypeOfService(customer.getTypeOfService());
            token.setTokenStatus("CREATED");
            token.setMessage("Operations to be performed:");
            if(token.getPriority() ==0 ){
                token.setPriority(3);
            }
            token.setServiceCounterId(serviceCounters.get(index).getId());
            return new ResponseEntity<>(repository.save(token), HttpStatus.CREATED);
        }else{

            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("Customer Id doesn't exist!!!");
        }
    }

    @RequestMapping(
            method = RequestMethod.GET)
    public ResponseEntity<Collection<Token>> getAllTokens() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET)
    public ResponseEntity<Token> getTokenWithId(@PathVariable Long id) {
        return new ResponseEntity<>(repository.findOne(id), HttpStatus.OK);
    }


    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.PUT)
    public ResponseEntity<Token> updateTokenFromDB(@PathVariable("id") long id, @RequestBody Token token) {

        Token currentToken = repository.findOne(id);
        currentToken.setId(token.getId());
        currentToken.setCustomerId(token.getCustomerId());
        currentToken.setTokenStatus(token.getTokenStatus());
        currentToken.setTypeOfService(token.getTypeOfService());
        currentToken.setPriority(token.getPriority());
        currentToken.setServiceCounterId(token.getServiceCounterId());
        currentToken.setMessage(token.getMessage());


        return new ResponseEntity<>(repository.save(currentToken), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE)
    public void deleteTokenWithId(@PathVariable Long id) {
        repository.delete(id);
    }

    @RequestMapping(
            method = RequestMethod.DELETE)
    public void deleteAllTokens() {
        repository.deleteAll();
    }

//    @RequestMapping(
//            path = "/updateStatus/",
////            value = "/{id}/{'status'}",
//            method = RequestMethod.PUT)
//    public void updateTokenStatus( @RequestBody Token token ){
//        repository.saveAndFlush(token);

//    }

    @RequestMapping(
            value = "updateStatus/{id}/{status}",
            method = RequestMethod.PUT)
    public void updateTokenStatusByID(@PathVariable("id") long id, @PathVariable("status") String status) {
        ResponseEntity<Token> resp = getTokenWithId(id);
        Token token = resp.getBody();
        token.setTokenStatus(status);
        repository.saveAndFlush(token);
    }


    @RequestMapping(
            value = "updateMessage/{id}",
            method = RequestMethod.PUT)
    public ResponseEntity<Token> updateTokenMessage(@PathVariable("id") long id, @RequestBody Token token) {

        Token currentToken = getTokenWithId(id).getBody();
        //token.setId(token.getId());
        currentToken.setMessage(currentToken.getMessage()+"/n"+token.getMessage());


        return new ResponseEntity<>(repository.saveAndFlush(currentToken), HttpStatus.OK);
    }

    public List<Token> getAllTokens(Long id) {
       return repository.findByServiceCounterId(id);
    }
}
