package com.kaluzny.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface TokenRepository extends JpaRepository<Token, Long> {
//    List<Token> findByName(String name);
}
