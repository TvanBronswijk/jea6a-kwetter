pipeline {
    agent none
    stages {
        stage('checkout') {
            agent any
            steps {
                checkout scm
            }
        }
        stage('Compile Source Code') {
            agent {
                docker {
                    image 'maven:3-alpine' 
                }
            }
            steps {
                sh "mvn clean compile"
            }
        }
        stage('Test Source Code') {
            agent {
                docker {
                    image 'maven:3-alpine' 
                }
            }
            steps {
                sh "mvn clean verify"
            }
        }
        stage('Build Docker Image') {
            agent {
                label 'docker'
            }
            steps {
                sh "docker build -t fontys/kwetter:latest ."
            }
        }
    }
}