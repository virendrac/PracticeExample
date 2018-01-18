## Plain REST API CRUD with Spring Boot and PostgreSQL.


[![Build Status](https://travis-ci.org/OKaluzny/spring-boot-rest-api-postgresql.svg?branch=master)](https://travis-ci.org/OKaluzny/spring-boot-rest-api-postgresql)

### Technology stack:

* Spring Boot;
* Spring Web;
* Spring Data;
* PostgreSQL database;
* Hibernate;
* Spring Security (as basic authentication).

#####To run this application use:

```bash
mvn spring-boot:run
```

### The view in the Postman:


User Name : user
Password : user

#http://localhost:8080/api/customers/display :a service that displays a list of service counters and token numbers


# http://localhost:8080/api/tokens/ getting list of all tokens
# http://localhost:8080/api/tokens/12 getting list of token with id = 12
# http://localhost:8080/api/tokens/updateStatus/1/CREATED

# http://localhost:8080/api/tokens/ POST
 {
        "customerId": 20,
        "typeOfService": "P"
 }


http://localhost:8080/api/tokens/updateMessage/{tokenId} : PUT:
{
    "message" : "Message to be appended on the token"
}
