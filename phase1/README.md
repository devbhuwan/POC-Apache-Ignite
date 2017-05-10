### PHASE-1 : POC Application Architecture using Apache Ignite for READ and WRITE

![alt text](phase-1-poc-architecture.png "PHASE-1 : POC Application Architecture")

### App1 - Corporate Service
 
- Check CURL and also see database data after each command execution to verify cache with database values. 
```
    curl -X POST http://localhost:8888/payment/add
    curl -X GET http://localhost:8888/payment/1
    curl -X DELETE http://localhost:8888/payment/1   
```

### App2 - Bank Service 
- Check CURL and also see database data after each command execution to verify cache with database values.
 (Note: please add one payment by using corporate service before perform action from Bank service)
 
```
    curl -X POST http://localhost:8889/payment/approve/1
    curl -X POST http://localhost:8889/payment/reject/1
```
