pipeline {
    agent any
    stages {
        stage("test") {
            steps {
                sh "docker-compose -f docker-compose.test.yml up --build --exit-code-from app"
                sh "docker-compose -f docker-compose.test.yml down"
                script {
                    def props = """prop1=test1
                    prop2=test2"""
                    writeFile(file: "application.properties", text: props)
                    def str = readFile(file: "application.properties")
                    println('Properties file content')
                    echo str
                }
            }
        }
    }
}


