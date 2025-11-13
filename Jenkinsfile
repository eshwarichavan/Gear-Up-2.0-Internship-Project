pipeline {
    agent {label "agent1"}

    environment{
        ECR_URI=credentials('ECR_URI')
        AWS_CREDS = credentials('aws-creds')
        AWS_DEFAULT_REGION='ap-south-1'
        SERVER_IP=credentials('SERVER_IP')

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
                    -Dsonar.projectKey=GearUp_GarageManagementSystem \
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
                sh '''
                 ssh -o StrictHostKeyChecking=no -i /home/ubuntu/new-key ubuntu@${SERVER_IP} \"
                    sh /home/ubuntu/deploy.sh ${ECR_URI} ${BUILD_NUMBER}

            \"
            '''
            }
            }

        }

        

    }

    

