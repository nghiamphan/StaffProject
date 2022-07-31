pipeline {
    agent any
    stages {
//         stage('Build') {
//             steps {
//                 sh "chmod +x -R ${env.WORKSPACE}"
//                 sh './mvnw clean install -DskipTests'
//             }
//         }

        stage("test") {
            steps {
                sh "docker-compose -f docker-compose.test.yml up --build --exit-code-from app"
                sh "docker-compose -f docker-compose.test.yml down"
            }
        }


