pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
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