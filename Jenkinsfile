pipeline {
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
                sh "mvn clean compile -B"
                archiveArtifacts artifacts: 'target/', fingerprint: true
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
                    sh 'mvn -s $SETTINGS clean verify sonar:sonar -B'
                }
                archiveArtifacts artifacts: 'target/surefire-reports/', fingerprint: true
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
                sh "mvn clean package docker:build -Pdocker -DskipTest"
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
                                    sh 'mvn -s $SETTINGS clean package deploy -DskipTests -B'
                }
                archiveArtifacts artifacts: 'target/kwetter.war', fingerprint: true
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
