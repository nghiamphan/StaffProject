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
//                 sh "docker network create -d bridge test-pipeline"
                script {
                    docker.image('postgres').withRun("-p 5432:5432 -e POSTGRES_USERNAME=postgres -e POSTGRES_PASSWORD=password -e POSTGRES_DB=dbtest  --network test-pipeline --name dbb") { c ->
                        sleep 6;
                        sh "chmod +x -R ${env.WORKSPACE}";
                        sh './mvnw test';
                    }
                }
//                 sh "chmod +x -R ${env.WORKSPACE}"
//                 sh './mvnw test'
            }
        }
    }
}