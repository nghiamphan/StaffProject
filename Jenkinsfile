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
            steps {
                bat './mvnw test'
            }
        }

        stage('Build Image') {
            steps{
                script {
                    dockerImage = docker.build registry
                }
            }
        }
    }
}