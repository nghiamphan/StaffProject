pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh './mvnw clean install -DskipTests --chmod +x mvnw'
            }
        }

        stage('Unit Test') {
            steps {
                sh './mvnw test'
            }
        }
    }
}