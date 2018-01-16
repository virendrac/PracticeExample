package com.kaluzny.web;

import com.kaluzny.domain.Token;
import com.kaluzny.domain.TokenRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Collection;

@RestController
@RequestMapping("/api/tokens")
public class TokenRestController {

    private TokenRepository repository;

    @Inject
    public void setRepository(TokenRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(
            method = RequestMethod.POST)
    public ResponseEntity<?> addToken(@RequestBody Token token) {
        return new ResponseEntity<>(repository.save(token), HttpStatus.CREATED);
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
        currentToken.setTypeOfService(token.getTypeOfService());
        currentToken.setPriority(token.getPriority());
        currentToken.setServiceCounterId(token.getServiceCounterId());


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
}
