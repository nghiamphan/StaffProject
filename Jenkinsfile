pipeline {
    agent any
    stages {
        stage('Build') {
            agent {
                docker { image 'openjdk' }
            }
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