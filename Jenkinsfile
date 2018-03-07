pipeline {
    agent {
        docker {
            image 'maven:3-alpine' 
        }
    }
    stages {
        stage('checkout') {
            steps {
                checkout scm
            }
        }
        stage('Compile Source Code') {
            steps {
                sh "mvn clean compile"
            }
        }
        stage('Test Source Code') {
            steps {
                sh "mvn clean verify"
            }
        }
        stage('Build Docker Image') {
            steps {
                sh "mvn clean package docker:build"
            }
        }
    }
}