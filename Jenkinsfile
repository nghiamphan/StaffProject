pipeline {
    agent any
    stages {
        agent { image 'openjdk' }
        stage('Build') {
            steps {
                sh "chmod +x -R ${env.WORKSPACE}"
                sh './mvnw clean install -DskipTests'
            }
        }

        stage('Unit Test') {
            agent {
                dockerfile true
            }
            steps {
                sh "chmod +x -R ${env.WORKSPACE}"
//                 sh './mvnw test'
            }
        }
    }
}