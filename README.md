# UrgencyService

## Introduction
Urgency service enables paramedics to locate the most relevant hospital in UK according to the pathology of their patient.

## Requirements
* JAVA 11
* Postman

## Execution of project
### Setup
* Fork the repository responsible for hospital management and deploy it though Springboot [here](https://github.com/cjour/HospitalManagementService).
* Fork the repository needed for security purposes and deploy it though Springboot [here](https://github.com/cjour/SecurityService).
* Fork the current repository and deploy it though Springboot. 
* Download GraphHopper API jar : https://repo1.maven.org/maven2/com/graphhopper/graphhopper-web/7.0/graphhopper-web-7.0.jar
* Download GraphHopper API config file : https://raw.githubusercontent.com/graphhopper/graphhopper/7.x/config-example.yml
* Dowload latest geo fabrik England file : https://download.geofabrik.de/europe/great-britain/england-latest.osm.pbf

### Deployment
Open five terminal and run micro services files through following commands :
* Spring boot jar -> Provide commands
* GraphHopper API -> `java -Ddw.graphhopper.datareader.file=england-latest.osm.pbf -jar *.jar server config-example.yml`

## API Endpoints
| HTTP Verbs | Endpoints | Action | Permission
| --- | --- | --- | --- |
| POST | /auth | To authenticate user and generate authentication token. | Public
| GET | /urgency | To locate the nearest hospital taking care of the pathology. | Restricted to authenticated users

## How to use the application ?
### Authenticate
To be able to use the application you need to authenticate yourself first.
In order to do so open postman, use the POST endpoint `http://localhost:8089/auth` with the following body request :
```
{
    "username": "clément",
    "password": "password"
}
```
This authentication request, if successful, generates a token returned in the header of the response associated to the key `token`. Save it for later.

### Use the urgency service
Once authenticated you can use the urgency service to get the closest hospital taking care of the provided disease.
In order to do so open postman, use the GET endpoint with your own parameters :
```
/latitude/longitude/pathology/ambulance_id
```
Finally select `Bearer Token` in the Authorization tab and paste your saved token.
Sending your request will return to you the closest hospital available according to the pathology of your patient.

## Technologies Used
* [Spring boot](https://spring.io/projects/spring-boot) An open source framework based on Java for microservices and web apps creation.
* [Java](https://www.java.com/fr/) A mutliplatform and object-oriented programming language.
