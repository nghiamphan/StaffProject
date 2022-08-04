## Run the project
To run the project on your machine, go to folder */Keycloak* and run

*docker-compose -f docker-compose.yml up*

This will start:
- a mock Postgres container whose database is populated by liquibase
- a Keycloak container which is connected to a Postgres database

Initial setup for Keycloak:
- Go to http://localhost:9080, log in with user = "admin" and password = "password"
- Create the following:
    - Realm: SpringBootKeycloak
    - Client: login-app
    - Roles: user and admin. Role 'admin' should be the composite role consists of role 'user'.
    - Create a user with role 'user' and a user with role 'admin'

Run the project from the IDE:
- Go to http://localhost:8080/staffs: no authentication needed
- Go to http://localhost:8080/projects: need to log in with a user of role 'user' or 'admin'
- Go to http://localhost:8080/staffProjects: need to log in with a user of role 'admin'

## Jenkins
To start Jenkins, go to folder */Jenkins* and run:

*docker-compose -f docker-compose.jenkins.yml up*

Setup for Jenkins:
- Go to http://localhost:7080/, log in, install recommended plugins
- Create a new pipeline project, connect to a git repository of the project. **Note**: Script path should be *Jenkins/Jenkinsfile*

Check [Official guide](https://www.jenkins.io/doc/book/installing/docker/) for more information.