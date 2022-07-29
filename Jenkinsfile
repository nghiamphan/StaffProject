pipeline {
//     agent any
    agent {
        docker { image 'postgres' }
    }
    stages {
        stage('Build') {
            steps {
                sh "chmod +x -R ${env.WORKSPACE}"
                sh './mvnw clean install -DskipTests'
            }
        }

        stage('Unit Test') {
//             agent {
//                 docker {
//                     image 'postgres'
//                     // Run the container on the node specified at the
//                     // top-level of the Pipeline, in the same workspace,
//                     // rather than on a new node entirely:
//                     reuseNode true
//                 }
//             }
            steps {
                sh './mvnw test'
            }
        }
    }
}