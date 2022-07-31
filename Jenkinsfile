pipeline {
    agent any
    stages {
        stage("test") {
            steps {
                sh "docker-compose -f docker-compose.test.yml up --build --exit-code-from app"
                sh "docker-compose -f docker-compose.test.yml down"
            }
        }
    }
}


