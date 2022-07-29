pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh "chmod +x -R ${env.WORKSPACE}"
                sh 'mvn clean install -DskipTests'
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