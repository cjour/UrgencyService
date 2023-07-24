# UrgencyService

### Introduction
Urgency service enables paramedics to locate the most relevant hospital in UK according to the pathology of their patient.

### Requirements
* JAVA 11
* Postman

### Execution of project
## Setup
* Download micro services jar -> Provide link.
* Download GraphHopper API jar : https://repo1.maven.org/maven2/com/graphhopper/graphhopper-web/7.0/graphhopper-web-7.0.jar
* Download GraphHopper API config file -> Provide link.
* Dowload latest geo fabrik England file : https://download.geofabrik.de/europe/great-britain/england-latest.osm.pbf

## Deployment
Open five terminal and run micro services files through following commands :
* Spring boot jar -> Provide commands
* GraphHopper API -> `java -Ddw.graphhopper.datareader.file=england-latest.osm.pbf -jar *.jar server config-example.yml`

### API Endpoints
| HTTP Verbs | Endpoints | Action | Permission
| --- | --- | --- | --- |
| POST | /auth | To authenticate user and generate authentication token. | Public
| GET | /urgency | To locate the nearest hospital taking care of the pathology. | Restricted to authenticated users

### How to use the application ?
## Authenticate
To be able to use the application you need to authenticate yourself first.
In order to do so open postman, use the POST endpoint and select `Basic Auth` in the Authorization tab.
Your body request must be 
{
    "username": "emergency",
    "password": "password"
}

### Technologies Used
* [Spring boot](https://spring.io/projects/spring-boot) An open source framework based on Java for microservices and web apps creation.
* [Java](https://www.java.com/fr/) A mutliplatform and object-oriented programming language.
