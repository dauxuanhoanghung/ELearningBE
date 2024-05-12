pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Copy Secret Files') {
            steps {
                sh """
                    # Copy tokens folder
                    sudo cp -rf /home/ubuntu/private-scp/secret/tokens/ ${WORKSPACE}/ELearning/

                    # Copy application.properties
                    sudo cp -f /home/ubuntu/private-scp/secret/application.properties ${WORKSPACE}/ELearning/src/main/resources/

                    # Copy client_secrets.json
                    sudo cp -f /home/ubuntu/private-scp/secret/client_secrets.json ${WORKSPACE}/ELearning/src/main/resources/
                """
            }
        }

        stage('Build Docker Compose') {
            steps {
                script {
                    docker.withDockerCompose('-f ELearning/compose.yml') {
                        sh 'docker compose build'
                    }
                }
            }
        }

        stage('Stop Old Containers') {
            steps {
                script {
                    docker.withDockerCompose('-f ELearning/compose.yml') {
                        sh 'docker compose down'
                    }
                }
            }
        }

        stage('Run Docker Compose') {
            steps {
                script {
                    docker.withDockerCompose('-f ELearning/compose.yml') {
                        sh 'docker compose up -d'
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
}
