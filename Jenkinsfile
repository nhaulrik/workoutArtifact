#!groovy

node {

    def scmVars
    def pomFile = "pom.xml"
    def version = currentBuild.number

    stage('Git clone') {
        scmVars = checkout scm
    }

    stage('build') {
        sh "mvn -B -f ${pomFile} -Dbuild.number=${version} -DskipTests compile"
    }

//    stage('Compile') {
//        sh 'mvn -B -DskipTests clean compile'
//    }
//    stage('Test') {
//        sh 'mvn -B test'
//    }
    stage('Build docker image') {
        sh "mvn package -DskipTests"
    }

}