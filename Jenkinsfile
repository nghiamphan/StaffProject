pipeline {
    agent any
    stages {
        stage("test") {
            steps {
                // sh "docker-compose -f docker-compose.test.yml up --build --exit-code-from app"
                // sh "docker-compose -f docker-compose.test.yml down"

                script {
                    def fileName = "src/main/resources/application.properties"
                    def newProps = """\nprop1=test1\nprop2=test2"""
                    writeFile(file: fileName, text: readFile(file: fileName) + newProps)
                    println('Properties file content')
                    echo readFile(file: "src/main/resources/application.properties")
                }
            }
        }
    }
}


