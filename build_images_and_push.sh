#!/bin/bash

eval $(aws ecr get-login --no-include-email)

docker build -t 217993088031.dkr.ecr.eu-west-2.amazonaws.com/vsadkov-phpapp-nginx:latest -f ./Dockerfile.nginx .
docker push 217993088031.dkr.ecr.eu-west-2.amazonaws.com/vsadkov-phpapp-nginx:latest
docker rmi 217993088031.dkr.ecr.eu-west-2.amazonaws.com/vsadkov-phpapp-nginx:latest

docker build -t 217993088031.dkr.ecr.eu-west-2.amazonaws.com/vsadkov-phpapp-phpfpm:latest -f ./Dockerfile.phpfpm .
docker push 217993088031.dkr.ecr.eu-west-2.amazonaws.com/vsadkov-phpapp-phpfpm:latest
docker rmi 217993088031.dkr.ecr.eu-west-2.amazonaws.com/vsadkov-phpapp-phpfpm:latest
