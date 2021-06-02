# poupachefchallenge

### Auto start

To start all project without other needs, you must to have docker compose and maven installed on your machine. On the project folder run the following commands:

```
mvn package
docker-compose up
```

Wait until the docker-compose command finalize, then the server must be up on the http://localhost:8080 with the correct database configuration and API's.

### Running step-by-step

To setup and execute the project, you first need to have Maven correctly installed and configured, and a MySQL instance. Change the variables on application.properties to correctly point to you database, then run this two commands to start project:

```shell
mvn clean install
mvn spring-boot:run
```

After the instalation and run command, the server must be up on http://localhost:8080


## Using
All the endpoints with his parameters are described in a high level on the postman collection [here](./postman.json). This API uses REST protocol with JSON.

# The project

The project has been maded with a hexagon architecture in mind, with CQRS, although some of his pratices are not present because the simple nature of the domain. I opt to use this patterns visioning a possible extension of features without much pain, the modularity of CQRS are a well suited way to do the work.

# I wanted to do it but I didn't

Some things i wanted to make but didn't because lack of time:
- Use event source on products;
- Set a good test coverage;
- Integrate spring-security to use OAuth