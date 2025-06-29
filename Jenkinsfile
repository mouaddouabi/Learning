pipeline {
    agent {
        docker {
            image 'maven:3.9.4-eclipse-temurin-17' // docker/compose:latest This image includes docker + compose plugin
            args '-v /var/run/docker.sock:/var/run/docker.sock' // mount docker socket
        }
    }
    tools {
        maven 'maven3' // Replace with your Jenkins Maven name
        jdk 'jdk17'        // Replace with your Jenkins JDK name
    }

    environment {
        COMPOSE_FILE = 'docker-compose.yml'
    }

    stages {

        stage('Checkout') {
            steps {
                git url: 'https://github.com/mouaddouabi/Learning.git', branch: 'main'
            }
        }

        stage('Build Spring Boot App') {
            steps {
                sh 'mvn clean package -DskipTests=false'
            }
        }

        stage('Rebuild & Deploy with Docker Compose') {
            steps {
                // Optional: Stop running containers if needed
                //sh 'docker-compose down'

                // Rebuild and restart the stack (app + SQL Server + Jenkins)
                sh 'docker compose up --build -d'
            }
        }

        stage('Check Logs') {
            steps {
                sh 'docker-compose logs --tail=20 app'
            }
        }
    }

    post {
        success {
            echo '✅ Application deployed successfully with Docker Compose.'
        }
        failure {
            echo '❌ Deployment failed. Check logs above.'
        }
    }
}
