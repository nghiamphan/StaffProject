pipeline {
    agent any
    stages {
        stage('Build') {
            agent {
                docker {
                    image 'maven:3.8.1-adoptopenjdk-11'
                    args '-v $HOME/.m2:/root/.m2'
                }
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