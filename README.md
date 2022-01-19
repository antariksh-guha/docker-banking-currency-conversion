<!-- How to run docker on local -->

1. Open Docker Desktop in windows:

2. Open PS in windows and execute:
docker-compose up

3. Open eureka and zipkin
    a. http://localhost:9411/zipkin <Zipkin - Distributed Tracing>
    b. http://localhost:8761/ <Eureka - Naming Server>

4. Imp localhost URLs:
    a. http://localhost:8000/currency-exchange/from/USD/to/INR <currency-exchange>
    b. http://localhost:8100/currency-conversion/from/USD/to/INR/quantity/10 <currency-conversion and also has feign>
    c. http://localhost:8765/currency-conversion/from/USD/to/INR/quantity/10 <through api-gateway>
    d. http://localhost:8765/currency-exchange/from/USD/to/INR/ <through api-gateway and also has feign proxy>
    c. http://localhost:8765/get <api gateway routes>
