To start Jenkins, run:

*docker-compose -f docker-compose.jenkins.yml*

Initial set up: go to http://localhost:7080/, log in, install recommended plugins.

Create a new pipeline, connect to git repository of the project.

Note: Script path should be *Jenkins/Jenkinsfile*

Check [Official guide](https://www.jenkins.io/doc/book/installing/docker/) for more information.