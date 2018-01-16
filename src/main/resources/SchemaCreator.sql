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
    type_of_service VARCHAR(1),
    priority INT DEFAULT 3,
    service_counter_id INT
);
