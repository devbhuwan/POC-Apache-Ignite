- Check CURL and also see database data after each command execution to verify cache with database values. 
```
    curl -X POST http://localhost:8888/payment/add
    curl -X GET http://localhost:8888/payment/1
    curl -X DELETE http://localhost:8888/payment/1   
```
