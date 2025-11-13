pipeline {
    agent {label "agent1"}

    environment{
        ECR_URI=credentials('ECR_URI')
        AWS_CREDS = credentials('aws-creds')
        AWS_DEFAULT_REGION='ap-south-1'
        SERVER_IP=credentials('SERVER_IP')
        // MAIL_PORT=587
        // MAIL_USERNAME=credentials('MAIL_USERNAME')
        // MAIL_PASSWORD=('MAIL_PASSWORD')
        // DB_URL=credentials('DB_URL_FLUTTER')
        // DB_USERNAME=credentials('DB_USERNAME_FLUTTER')
        // DB_PASSWORD=credentials('DB_PASSWORD_FLUTTER')
        // JWT_REFRESH_EXPIRATION_MS=credentials('JWT_REFRESH_EXPIRATION_MS')

    }

    stages {

        stage('AWS Login') {
            
            steps {
            
                sh '''
                aws configure set aws_access_key_id  $AWS_ACCESS_KEY_ID
                aws configure set aws_secret_access_key $AWS_SECRET_ACCESS_KEY
                aws configure set region $AWS_DEFAULT_REGION
                aws sts get-caller-identity

                '''
            
        }
        }


        stage('Build') {
            steps {
                sh '''
                export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
                export PATH=$JAVA_HOME/bin:$PATH
                mvn clean install -DskipTests
                '''
            }

        }

        stage('Sonarqube static code analysis') {
            steps {
                withSonarQubeEnv('Sonar') { 
               sh '''
                mvn sonar:sonar \
                    -Dsonar.projectKey=inventory_iosbackend \
                    -Dsonar.host.url=$SONAR_HOST_URL \
                    -Dsonar.login=$SONAR_AUTH_TOKEN
            '''
            }}

        }

        stage('Build docker image and push to ECR') {
            steps {
                sh '''
                    aws ecr get-login-password --region ap-south-1 | docker login --username AWS --password-stdin ${ECR_URI}
                    sudo docker build -t flutterbackend:${env.BUILD_NUMBER} .
                    sudo docker tag flutterbackend:${env.BUILD_NUMBER} ${ECR_URI}/flutterbackend:${env.BUILD_NUMBER}
                    sudo docker push ${ECR_URI}/flutterbackend:${env.BUILD_NUMBER}

                '''
            }

        }

        stage('Docker container run'){
            steps {
                 withCredentials([string(credentialsId: 'flutter_creds', variable: 'MY_CREDS')]) {
        
                sh '''
                set -a
                source <(echo "$MY_CREDS")
                set+a
                ssh -i /home/ubuntu/new-key.pem ubuntu@${SERVER_IP} \"
                    aws ecr get-login-password --region ap-south-1 | docker login --username AWS --password-stdin ${ECR_URI}
                    sudo docker pull ${ECR_URI}/flutterbackend:${env.BUILD_NUMBER}
                    sudo docker stop fluttercont || true
                    sudo docker rm fluttercont || true
                    sudo docker run -d --name fluttercont -p 8001:8080 \\
                    -e  MAIL_PORT=587 \\
                    -e MAIL_USERNAME=${MAIL_USERNAME} \\
                    -e MAIL_PASSWORD=${MAIL_PASSWORD} \\
                    -e DB_URL=${DB_URL_FLUTTER} \\
                    -e DB_USERNAME=${DB_USERNAME_FLUTTER} \\
                    -e DB_PASSWORD=${DB_PASSWORD_FLUTTER} \\
                    -e JWT_REFRESH_EXPIRATION_MS=${JWT_REFRESH_EXPIRATION_MS} \\
                     ${ECR_URI}/flutterbackend:${env.BUILD_NUMBER}

                    \"
                '''

            }}

        }

        

    }

    

}