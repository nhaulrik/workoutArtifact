pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }
//        stage('Test') {
//            steps {
//                script {
//                    //Execute Unit tests
//                    container('maven') {
//                        sh 'mvn test'
//                        //Code coverage analysis
//                        jacoco changeBuildStatus: false, exclusionPattern: '**/*Test*.class'
//                    }
//                }
//            }
//        }
    }
}