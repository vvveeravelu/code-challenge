# Find if a path Connected between two cities

Spring Boot application enabling client to determine if two cities are connected

List of roads is available in the city.txt file in src/main/resources. File contains a list of city pairs (one pair per line, comma separated), which indicates that there&#39;s a road between those cities.

It will be deployed as a spring-boot app and expose one endpoint:

[http://localhost:8080/connected?origin=city1&amp;destination=city2](http://localhost:8080/connected?origin=city1&amp;destination=city2)

Your program should respond with &#39;yes&#39; if city1 is connected to city2, &#39;no&#39; if city1 is not connected to city2. Any unexpected input should result in a &#39;no&#39; response.

_Example:_

city.txt content is:

Boston, New York

Philadelphia, Newark

Newark, Boston

Trenton, Albany

- [http://localhost:8080/connected?origin=Boston&amp;destination=Newark](http://localhost:8080/connected?origin=Boston&amp;destination=Newark)
- Should return yes
- [http://localhost:8080/connected?origin=Boston&amp;destination](http://localhost:8080/connected?origin=Boston&amp;destination)=Philadelphia
- Should return yes
- [http://localhost:8080/connected?origin=Philadelphia&amp;destination=Albany](http://localhost:8080/connected?origin=Philadelphia&amp;destination=Albany)
- Should return no


# Build from source

    mvn clean install

# Run the application

Using maven Spring Boot plugin   

    mvn spring-boot:run

Using Java command line 

    java -jar target/code-challenge-0.0.1-SNAPSHOT.jar


# Provide your own comma seperated cities file

Using maven Spring Boot plugin  

    mvn spring-boot:run -Ddata.file=/tmp/ mytestcities.txt

Using Java command line  

    java -Ddata.file=/tmp/mytestcities.txt -jar target/code-challenge-0.0.1-SNAPSHOT.jar

### Swagger

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)