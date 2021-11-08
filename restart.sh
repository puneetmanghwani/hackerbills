#!/bin/sh



if [ $# -ne 3 ]
then
       echo "usage: ./$0 <server_port> <profile>  <logLevel>"
       exit 1
fi


serverPort=$1
profile=$2
logLevel=$3

instanceId=1
logRoot=./logs/



waitTime=5

currentDir=$(dirname -- $0)

echo $currentDir

echo "Shutting down old process if present..."
sh $currentDir/shutdown.sh $logRoot $instanceId
echo "Waiting $waitTime seconds..."

if [ -d "./logs/1" ]
then
  rm -r ./logs/1
  sleep 1
else
  echo "Log folder does not exist"
fi

sleep $waitTime
sh $currentDir/start.sh $1 $2 $3


