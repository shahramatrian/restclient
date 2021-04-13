# A simple REST client
The client uses Spring Boot 2 and Jackson to read data from https://dashboard.healthit.gov/api/open-api.php endpoint and print out, by state and in descending order, the percentage of hospitals that support basic EHR notes in the year 2014.

It uses Maven for dependency management.

Usage:
* To run it via CLI: ./mvnw spring-boot:run
* To see results in a simple web page: 
   * run the application (./mvnw spring-boot:run)
   * go to: http://localhost:8080/statehealth
* To see the result as a web service:
   * run the application (./mvnw spring-boot:run)
   * use curl or postamn with this endpoint: http://localhost:8080/api/statehealth

