# cqrs-design-pattern-demo

- create product event :
```
curl --location 'http://localhost:9191/products' \
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
curl --location --request PUT 'http://localhost:9191/products/1' \
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
