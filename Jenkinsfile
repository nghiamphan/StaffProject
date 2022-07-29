pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh "chmod +x -R ${env.WORKSPACE}"
                sh './mvnw clean install -DskipTests'
            }
        }

        stage('Unit Test') {
            steps {

                script {
                    docker.image('postgres').withRun("-p 5432:5432 -e POSTGRES_USERNAME=postgres -e POSTGRES_PASSWORD=password -e POSTGRES_DB=dbtest --name dbd") { c ->
//                         sh "chmod +x -R ${env.WORKSPACE}";
                        sh "docker logs ${c.id}"
                        sh './mvnw test';
                    }
                }
//                 sh "chmod +x -R ${env.WORKSPACE}"
//                 sh './mvnw test'
            }
        }
    }
}