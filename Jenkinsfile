pipeline {
    agent any
    stages {
        stage("test") {
            steps {
                sh "docker-compose -f docker-compose.test.yml up --build --exit-code-from app"
                sh "docker-compose -f docker-compose.test.yml down"
                script {
                    def props = """\nprop1=test1\nprop2=test2"""
                    writeFile(file: "application.properties", text: readFile(file: "application.properties") + props)
                    def str = readFile(file: "application.properties")
                    println('Properties file content')
                    echo str
                }
            }
        }
    }
}


