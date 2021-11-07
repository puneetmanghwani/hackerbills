#!/bin/sh


if [ $# -ne 7 ]
then
       echo "usage: ./$0 <server_port> <profile> <size Of Log file in MB> <number of rollover files> <logLevel> <instance_id> <log_root>"
       exit 1
fi




instanceId=$6
logRoot=$7



waitTime=5

currentDir=$(dirname -- $0)

echo $currentDir

echo "Shutting down old process if present..."
sh $currentDir/production_shutdown.sh $logRoot $instanceId
echo "Waiting $waitTime seconds..."


sleep $waitTime
sh $currentDir/production_start.sh $@


