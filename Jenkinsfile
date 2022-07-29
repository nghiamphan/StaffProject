pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh "chmod +x -R ${env.WORKSPACE}"
                sh './mvnw clean install'
            }
        }

        stage('Unit Test') {
            steps {
                sh "chmod +x -R ${env.WORKSPACE}"
                sh './mvnw test'
            }
        }
    }
}