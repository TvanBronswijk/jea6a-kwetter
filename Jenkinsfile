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
                archiveArtifacts artifacts: 'target/', fingerprint: true
            }
        }
        stage('Test Source Code') {
            steps {
                sh "mvn clean verify -B"
                archiveArtifacts artifacts: 'target/surefire-reports/', fingerprint: true
            }
        }
        stage('Build Docker Image') {
            steps {
                sh "mvn clean package docker:build -Pdocker -DskipTest"
            }
        }
        stage('Deploy WAR to Artifactory') {
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
                    image '17.09-dind'
                    args '-v /var/run/docker.sock:/var/run/docker.sock'
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