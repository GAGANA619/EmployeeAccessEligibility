pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                bat 'mvn.cmd clean compile'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn.cmd test'
            }
        }

        stage('Package') {
            steps {
                bat 'mvn.cmd package -DskipTests'
            }
        }
    }

    post {
        always {
            junit 'target/surefire-reports/*.xml'
        }

        success {
            echo 'Employee Access Eligibility CI/CD completed successfully.'
        }

        failure {
            echo 'Pipeline failed. Check the console output.'
        }
    }
}