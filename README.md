# Authentication and Authorization Service


This service is used to authenticate and authorize a user.

Two types of authentication methods that are considered to be currently included in this microservice are:

- Username/Password Authentication
- SSO (OpenID) \[Later Stage\]

JWT is used for user authorization.

Each time the JWT is used for authorization, the expiry is checked and if not expired, the roles (or permissions) are included in the 'X-Permission-Header' and sent to the intended microservice. If the user is disabled, then the API gateway refrains from forwarding the request to the microservice.

Each microservice also checks for the 'X-Permission-Header' and only processes the request if it has sufficient roles (or permissions).


## API Endpoints

| Functionality | REST Endpoint | Method | Parameters |
| --- | --- | --- | --- |
| Register User |  `/register/user` | **GET** | |
| Login User |  `/login/user` | **POST** | |
| Verify User Email |  `/register/verify/email` | **PUT** | emailToken |
| Verify User Mobile |  `/register/verify/mobile` | **PUT** | mobileToken |

## Configuration

Edit the properties of **application.yml** file

```yaml
# Eureka properties
eureka:
  client:
    fetch-registry: true
    register-with-eureka: true
    service-url:
      defaultZone: address of the eureka server (Eg: http://localhost:8761/eureka)
  instance:
    hostname: specify the hostname of service here (Eg: localhost)

# Server properties
server:
  port: port in which the auth service is to run (Eg: 8091)

# Application properties
spring:
  application:
    name: name of the application (Eg: AUTHN-AUTHZ-SERVICE)
# MongoDB properties
  data:
    mongodb:
      database: mongoDB database name (Eg: testWorkingDB)
      host: name of mongoDB host (Eg: localhost)
      port: port in which mongoDB is being run (Eg: 27017)
```

## Local Deployment

Service Registry should be started for successful execution of all queries.

In application.yml file, change the properties

| Property | Value | Example |
| --- | --- | --- |
| eureka_hostname | hostname of eureka server | service-registry |
| service_hostname | hostname of service (try to use the same as in docker-compose) | auth-service |
| mongodb_hostname | hostname of mongodb | auth-db |
| mongodb\_database\_name | database name | authDB |

### Using Docker

Create docker bridge network: `docker network create -d bridge pigihi-network`

docker-compose can be used to run the application and the corresponding mongodb instance

1.  Go to project folder
2.  Open terminal and run `docker-compose up`
3.  The application can be accessed at localhost:8091 (port 8091 is set in docker-compose)
4.  MongoDB port is set to 27016

To run only the application

1.  Go to project folder
2.  Open terminal and run `docker build .`
3.  Run `docker run -p 8091:8091 docker_image_name`
4.  The application can be accessed at localhost:8091

### Using Gradle

MongoDB should be run seperately and the configurations should be updated in application.yml

1.  Go to project folder
2.  Open terminal and run `./gradlew build`
3.  Run `./gradlew bootRun`

* * *
