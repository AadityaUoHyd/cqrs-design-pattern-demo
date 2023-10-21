# CQRS Design Pattern
CQRS stands for "Command and Query Responsibility Segregation". Basically this pattern separates read and update operations for a database.

Normally, in monolithic applications, most of time we have 1 database and this database should respond both query and update operations.
That means a database is both working for complex join queries, and also perform CRUD operations. But if the application goes more complex
this query and crud operations will be also is going to be un-manageable situation. <br><br>
So reading and writing database has different approaches that we can define different strategy to handle that operation. In order to that
CQRS offers to use “separation of concerns” principles and separate reading database and the writing database with 2 database. By this way we can
even use different database for reading and writing database types like using no-sql for reading and using relational database for crud operations.<br><br>
With CQRS, We can individually scale Query Responsibility based services in microservices, which have reading capability(with HTTP GET) and usually have more load comparatively,
than Command Responsibility based services which have writing capability(with HTTP POST, PUT and DELETE).

# cqrs-design-pattern-demo
We could've used two different databases but here in this demo we are using two seperate table `(PRODUCT_COMMAND, and PRODUCT_QUERY)` in same db schema `product`, 
one for `"product-command-service"` based data, and another is `"product-query-service"` based data. <br>
We are using Apache KAFKA, to sync both table data. Here I'm doing demo, with create and update product with `"product-command-service"` that will 
invoke POST and PUT request, and later via Kafka those data will be sent to `"product-query-service"` where we can find those data invoking GET request.

- create product event :
```
curl --location 'http://localhost:9002/products' \
--header 'Content-Type: application/json' \
--data '{
    "type": "CreateProduct",
    "product": {
        "name": "Books",
        "description": "Learn Spring Boot and Microservices",
        "price": 1200
    }
}'
```

- update product event :
```
curl --location --request PUT 'http://localhost:9002/products/1' \
--header 'Content-Type: application/json' \
--data '{
    "type": "UpdateProduct",
    "product": {
        "id": 1,
        "name": "Mobile",
        "description": "iPhone-14",
        "price": 90000.0
    }
}'
```
