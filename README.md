# Kwetter

This is a Twitter Clone developed for Fontys Hogescholen ICT, course JEA6a.

### Prerequisites

* JDK 1.8.0_152
* Java EE 8
* Maven 3.5.0+
* Glassfish 5.0
* Microsoft SQL Server 2016
* (Optional) Docker Community Edition 17.12.0-ce-win47

### Installing

To run Kwetter download the source code and build the WAR file using the following command:

```
mvn clean package -Plocal
```

To create the Docker image, use the following command:

```
mvn clean package docker:build -Pdocker
```

## Testing

To run the unit test suite, use the following command:
```
mvn clean test
```

## Deployment

Kwetter can be deployed either on Glassfish or via Docker.

Deploying on Glassfish can be done by copying the compiled WAR file to the following location and starting the server:

```
GLASSFISH_HOME/glassfish/domains/domain1/autodeploy/
```

Deploy using Docker with the following command:

```
docker stack deploy -c docker/docker-stack.yml kwetter
```

## Built With

* [Microsoft SQL Server](https://www.microsoft.com/en-us/sql-server/sql-server-2016) - Database
* [GlassFish](https://javaee.github.io/glassfish/) - Application Server
* [Maven](https://maven.apache.org/) - Dependency Management
* [Docker](https://www.docker.com/) - Containerised applications


## Author

* **Tobi van Bronswijk** - *Student Software Engineer* - [GitHub](https://github.com/sternold)
