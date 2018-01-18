CREATE TABLE customer (
    id int,
    name VARCHAR(40),
    phone INT,
    address VARCHAR(100),
    type_of_service VARCHAR(1)
);

CREATE TABLE token (
    id INT  ,
    customer_id INT ,
    token_status VARCHAR (10) DEFAULT 'CREATED',
    type_of_service VARCHAR(1),
    priority INT DEFAULT 3,
    service_counter_id INT,
    message VARCHAR(1000) DEFAULT 'Operations to be performed:'
);

CREATE TABLE Service_Counter (
    id INT ,
    counter_type VARCHAR(1) DEFAULT 'R'

);