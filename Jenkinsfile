pipeline {
    environment {
        registry = "docker-test"
        registryCredential = 'dockerhub'
        dockerImage = ''
    }
    agent any
    stages {
        stage('Build') {
            steps {
                bat './mvnw clean install -DskipTests'
            }
        }

        stage('Unit Test') {
            agent {
                docker {
                    image 'postgres'
                    // Run the container on the node specified at the
                    // top-level of the Pipeline, in the same workspace,
                    // rather than on a new node entirely:
                    reuseNode true
                }
            }
            steps {
                bat './mvnw test'
            }
        }
    }
}