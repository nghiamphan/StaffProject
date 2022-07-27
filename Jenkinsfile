pipeline {
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
        
    }
}