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
                    sudo cp -f /home/ubuntu/private-scp/secret/client_secret.json ${WORKSPACE}/ELearning/src/main/resources/
                """
            }
        }

        stage('Build Docker Compose') {
            steps {
                sh """
                    cd ${WORKSPACE}/ELearning
                    docker compose -f compose.yml build
                """
            }
        }

        stage('Stop Old Containers') {
            steps {
                sh """
                    cd ${WORKSPACE}/ELearning
                    docker compose -f compose.yml down
                """
            }
        }

        stage('Run Docker Compose') {
            steps {
                sh """
                    cd ${WORKSPACE}/ELearning
                    docker compose -f compose.yml up -d
                """
            }
        }

        stage('Clean up old images') {
            steps {
                script {
                    sh 'sudo docker images prune --filter "until=24h"'
                }
            }
        }

    }
}
