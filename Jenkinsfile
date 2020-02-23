pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v /root/.m2:/root/.m2'
        }
    }
    stages {
        stage('Compile') {
            steps {
                sh 'mvn -B -DskipTests clean compile'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn -B test'
            }
        }
        stage('Publish') {
            steps {
                sh "mvn -B deploy -DskipTests"
//                sh "docker-compose up -d" virker ikke!!! fordi jenkins ikke kan køre docker
            }
        }
    }
}

//step([$class: 'DockerComposeBuilder', dockerComposeFile: 'docker-compose.yml', option: [$class: 'StartService', scale: 1, service: 'application'], useCustomDockerComposeFile: true])