pipeline {
    agent any
    stages {
        stage("test") {
            steps {
//                 sh "docker-compose -f docker-compose.test.yml up --build --exit-code-from app"
//                 sh "docker-compose -f docker-compose.test.yml down"
                script {
                       rm "application.properties"
//                     def props = readProperties(file: "application.properties")
//                     if (props['prop1'] !== null) {
//                         def newProps = """\nprop1=test1"""
//                         writeFile(file: "application.properties", text: readFile(file: "application.properties") + newProps)
//                     }
//                     def str = readFile(file: "application.properties")
//                     println('Properties file content')
//                     echo str
                }
            }
        }
    }
}


