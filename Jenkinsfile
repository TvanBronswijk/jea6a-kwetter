pipeline {
    environment {
        server = "server"
    }

    agent any
    stages {
        stage('checkout') {
            steps {
                checkout scm
            }
        }
        stage('Compile Source Code') {
            agent {
                docker {
                    image 'maven:3-alpine'
                    reuseNode true
                }
            }
            steps {
                sh "cd ${server} && mvn clean compile -B"
            }
        }
        stage('Test Source Code') {
            agent {
                docker {
                    image 'maven:3-alpine'
                    reuseNode true
                }
            }
            steps {
                configFileProvider([configFile(fileId: 'maven_settings', variable: 'SETTINGS')]) {
                    sh 'cd ${server} && mvn -s $SETTINGS clean verify sonar:sonar -B'
                }
            }
        }
        stage('Build Docker Image') {
            agent {
                docker {
                    image 'maven:3-alpine'
                    reuseNode true
                }
            }
            steps {
                sh "cd ${server} && mvn clean package docker:build -DskipTest"
            }
        }
        stage('Deploy WAR to Artifactory') {
            agent {
                docker {
                    image 'maven:3-alpine'
                    reuseNode true
                }
            }
            steps {
                configFileProvider([configFile(fileId: 'maven_settings', variable: 'SETTINGS')]) {
                                    sh 'cd ${server} && mvn -s $SETTINGS clean package deploy -DskipTests -B'
                }
            }
        }
        stage('Pull Docker Images') {
            agent {
                docker {
                    image 'docker:17.12-dind'
                    args '-v /var/run/docker.sock:/var/run/docker.sock'
                    reuseNode true
                }
            }
            when {
                branch 'master'
            }
            steps {
                sh 'docker pull microsoft/mssql-server-linux:latest'
            }
        }
        stage('Deploy Stack to Docker Daemon') {
            agent {
                docker {
                    image 'docker:17.12-dind'
                    args '-v /var/run/docker.sock:/var/run/docker.sock'
                    reuseNode true
                }
            }
            when {
                branch 'master'
            }
            steps {
                sh 'docker stack deploy -c docker/docker-stack.yml kwetter'
            }
        }
    }
}
