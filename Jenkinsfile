pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh "chmod +x -R ${env.WORKSPACE}"
                sh './mvnw clean install -DskipTests'
            }
        }

        stage('Unit Test') {
            agent {
                docker {
                    image 'postgres:latest'
                }
            }
            steps {
                sh './mvnw test'
            }
        }
    }
}