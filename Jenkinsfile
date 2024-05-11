pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build Docker Compose') {
            steps {
                script {
                    docker.withDockerCompose('-f ELearning/docker-compose.yml') {
                        sh 'docker-compose build'
                    }
                }
            }
        }

        stage('Stop Old Containers') {
            steps {
                script {
                    docker.withDockerCompose('-f ELearning/docker-compose.yml') {
                        sh 'docker-compose down'
                    }
                }
            }
        }

        stage('Run Docker Compose') {
            steps {
                script {
                    docker.withDockerCompose('-f ELearning/docker-compose.yml') {
                        sh 'docker-compose up -d'
                    }
                }
            }
        }

        stage('Clean up old images') {
            steps {
                script {
                    sh 'docker images prune --filter "until=24h"'
                }
            }
        }

    }

    post {
        always {
        }
    }
}
