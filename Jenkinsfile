pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v /root/.m2:/root/.m2'
        }
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean build'
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
            }
        }
    }
}