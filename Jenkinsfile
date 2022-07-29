// pipeline {
//     agent any
//     stages {
//         stage('Build') {
//             steps {
// //                 sh "chmod +x -R ${env.WORKSPACE}"
//                 sh './mvnw clean install -DskipTests'
//             }
//         }
//
//         stage('Unit Test') {
//             steps {
//
//                 script {
//                     docker.image('postgres').withRun("-p 5432:5432 -e POSTGRES_USERNAME=postgres -e POSTGRES_PASSWORD=password -e POSTGRES_DB=dbtest --name dbd") { c ->
// //                         sh "chmod +x -R ${env.WORKSPACE}";
// //                         sleep 5;
// //                         sh "docker logs ${c.id}"
//                         sh script: """
//                             sleep 5
//                             pg_isready -h localhost
//                         """
//                         sh './mvnw test';
//                     }
//                 }
// //                 sh "chmod +x -R ${env.WORKSPACE}"
// //                 sh './mvnw test'
//             }
//         }
//     }
// }

node {
    stage('build') {
        sh './mvnw clean install -DskipTests'
    }

    stage('test') {
        docker.image('postgres').withRun("-p 5432:5432 -e POSTGRES_USERNAME=postgres -e POSTGRES_PASSWORD=password -e POSTGRES_DB=dbtest  --name dbb") { c ->
            sleep 5
            sh 'pg_isready -h localhost'
            sh './mvnw test'
        }
    }
}