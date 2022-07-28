pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'chmod +x mvnw'
                sh './mvnw clean install -DskipTests'
            }
        }

        stage('Unit Test') {
            steps {
                sh './mvnw test'
            }
        }
    }
}