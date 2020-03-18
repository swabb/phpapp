node(label: 'master'){
    stage('Get repo'){
        println('Stage: ')
        sh "rm -rf ${WORKSPACE}/*"
        sh "mkdir ${WORKSPACE}/build"
        dir('build'){
            git branch: "${REPO_BRANCH}", url: "https://github.com/swabb/phpapp.git"
            withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'aws_jenkins', 
                            usernameVariable: 'AWS_ACCESS_KEY_ID', passwordVariable: 'AWS_SECRET_ACCESS_KEY']]) {

                sh 'aws ecr describe-repositories --region eu-west-2'
                sh "set +x && \$(aws ecr get-login --region eu-west-2 --no-include-email)"

                sh "docker build -t 217993088031.dkr.ecr.eu-west-2.amazonaws.com/vsadkov-phpapp-nginx:latest -f ./Dockerfile.nginx ."
                sh "docker push 217993088031.dkr.ecr.eu-west-2.amazonaws.com/vsadkov-phpapp-nginx:latest"
                sh "docker rmi 217993088031.dkr.ecr.eu-west-2.amazonaws.com/vsadkov-phpapp-nginx:latest"
                
                sh "docker build -t 217993088031.dkr.ecr.eu-west-2.amazonaws.com/vsadkov-phpapp-phpfpm:latest -f ./Dockerfile.phpfpm ."
                sh "docker push 217993088031.dkr.ecr.eu-west-2.amazonaws.com/vsadkov-phpapp-phpfpm:latest"
                sh "docker rmi 217993088031.dkr.ecr.eu-west-2.amazonaws.com/vsadkov-phpapp-phpfpm:latest"

                if (REDEPLOY == 'true') {
                    sh "kubectl delete -f phpapp-env.yaml"
                    sh "kubectl delete -f phpapp-deployment.yaml"
                }
                sh "kubectl create -f phpapp-env.yaml"
                sh "kubectl create -f phpapp-cert.yaml"
                sh "kubectl create -f phpapp-deployment.yaml"
                
                loadBalancer = sh returnStdout: true, script: "kubectl get svc -A | grep 'LoadBalancer' | awk '{print \$5}'"
                println("LoadBalancer address: ${loadBalancer}")

            }
        }
    }
}
