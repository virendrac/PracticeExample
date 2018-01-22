package com.kaluzny.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface TokenRepository extends JpaRepository<Token, Long> {
//    List<Token> findByName(String name);

//    void VOID
//    void updateTokenStatus(Token token);
        List<Token> findByServiceCounterId (long serviceCounterId);
        List<Token> findByServiceCounterIdAndTokenStatusOrderByPriorityAsc (long serviceCounterId , String tokenStatus);
        List<Token> findByServiceCounterIdAndTokenStatusLessThanOrderByPriorityAsc (long serviceCounterId , String tokenStatus);
}
