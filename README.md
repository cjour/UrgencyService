# UrgencyService

## Introduction
Urgency service enables paramedics to locate the most relevant hospital in UK according to the pathology of their patient.

## Requirements
* JAVA 11
* Spring Boot 2.7.9
* Postman

## Execution of project
### Setup
* Clone the repository responsible for hospital management [here](https://github.com/cjour/HospitalManagementService).
* Clone the repository needed for security purposes [here](https://github.com/cjour/SecurityService).
* Clone the current repository. 
* Download GraphHopper API jar : [here](https://repo1.maven.org/maven2/com/graphhopper/graphhopper-web/7.0/graphhopper-web-7.0.jar).
* Download GraphHopper API config file : [here](https://raw.githubusercontent.com/graphhopper/graphhopper/7.x/config-example.yml).
* Dowload latest geo fabrik England file : [here](https://download.geofabrik.de/europe/great-britain/england-latest.osm.pbf).

### Deployment
Each repository cloned must be deploy through Spring Boot.

Open a terminal and run GraphHopper through following command :

```
java -Ddw.graphhopper.datareader.file=england-latest.osm.pbf -jar *.jar server config-example.yml
```

## API Endpoints
| HTTP Verbs | Endpoints | Action | Permission
| --- | --- | --- | --- |
| POST | /auth | To generate authentication token. | Public
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
Once authenticated you can use the urgency service to get the closest hospital taking care of the provided [pathology](https://s3-eu-west-1.amazonaws.com/course.oc-static.com/projects/Architecte+Logiciel+FR/P11/Donne%CC%81es+de+re%CC%81fe%CC%81rence+sur+les+spe%CC%81cialite%CC%81s+NHS.pdf).
In order to do so open postman, use the GET endpoint with your own parameters :
```
http://localhost:8081/urgency/latitude/longitude/pathology/ambulance_id
```
Finally select `Bearer Token` in the Authorization tab and paste your saved token.
Sending your request will return to you the closest hospital available according to the pathology of your patient.

Hereunder you can find an example of a request for an authenticated user :
```
http://localhost:8081/urgency/51.585049/-0.175270/Allergy/45
```

## Technologies Used
* [Spring boot](https://spring.io/projects/spring-boot) An open source framework based on Java for microservices and web apps creation.
* [Java](https://www.java.com/fr/) A mutliplatform and object-oriented programming language.
* [GraphHopper](https://www.graphhopper.com/) A distance calculation service.
