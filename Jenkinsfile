pipeline {
    agent any
    environment {
        SPRING_DATASOURCE_URL = '/var/run/postgresql/.s.PGSQL.5432/dbtest'
        SPRING_DATASOURCE_USERNAME = 'postgres'
        SPRING_DATASOURCE_PASSWORD = 'password'
    }
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
                    docker.image('postgres').withRun("-p 5432:5432 -e POSTGRES_USERNAME=postgres -e POSTGRES_PASSWORD=password -e POSTGRES_DB=dbtest --net jenkins") { c ->
//                         sh "chmod +x -R ${env.WORKSPACE}";
                        sleep 5;
                        sh "docker logs ${c.id}"
//                         sh script: """
//                             sleep 5
//                             pg_isready -h localhost
//                         """
                        sh './mvnw test';
                    }
                }
//                 sh "chmod +x -R ${env.WORKSPACE}"
//                 sh './mvnw test'
            }
        }
    }
}

// node {
//     stage('build') {
//         sh './mvnw clean install -DskipTests'
//     }
//
//     stage('test') {
//         docker.image('postgres').withRun("-p 5432:5432 -e POSTGRES_USERNAME=postgres -e POSTGRES_PASSWORD=password -e POSTGRES_DB=dbtest  --name db --network jenkins") { c ->
//             sleep 5
//             sh 'pg_isready -h localhost'
//             sh './mvnw test'
//         }
//     }
// }