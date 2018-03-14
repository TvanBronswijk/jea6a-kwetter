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
                sh "mvn clean compile -B"
            }
        }
        stage('Test Source Code') {
            steps {
                sh "mvn clean verify -B"
            }
        }
        stage('Deploy to Artifactory') {
            steps {
                configFileProvider([configFile(fileId: 'maven_settings', variable: 'SETTINGS')]) {
                                    sh 'mvn -s $SETTINGS clean package deploy -DskipTests -B'
                }
            }
        }
        stage('Build Docker Image') {
            steps {
                sh "mvn clean package docker:build -Pdocker -DskipTest"
            }
        }
    }
}