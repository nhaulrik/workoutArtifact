#!groovy

node {
//    agent {
//        docker {
//            image 'maven:3-alpine'
//            args '-v /root/.m2:/root/.m2'
//        }
//    }
    stage('Compile') {
        sh 'mvn -B -DskipTests clean compile'
    }
//    stage('Test') {
//        sh 'mvn -B test'
//    }
    stage('Build docker image') {
        sh "mvn -B clean package -DskipTests"
    }


}