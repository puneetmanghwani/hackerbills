#!/bin/sh
#We compulsorily need the parameters listener.port , server.port, and device.type
#Optional parameters are hostname

#Take command line argument -listener_Port
#Do validations, there should be at least 3 arguments

if [ $# -ne 7 ]
then
       echo "usage: ./$0 <server_port> <profile> <size Of Log file in MB> <number of rollover files> <logLevel> <instance_id> <log_root>"
       exit 1
fi




server_port=$1
profile=$2
sizeOfLogFile="$3 MB"
rollOverFiles=$4
logLevel=$5
instanceID=$6
logging_dir=$7/$instanceID


currentDir=$(dirname -- $0)
echo $currentDir


# Logging_dir is for localhost.
mkdir -p $logging_dir
. /etc/profile

logFileName="sitescreation.log"

#exit 0

nohup java -Dspring.cloud.config.uri=http://localhost:8085 -Dspring.profiles.active=$profile  -Dmax.file.size="$sizeOfLogFile" -Dlog.file.name="$logging_dir/$logFileName" -Dlog.file.pattern="$logging_dir/\$\${date:yyyy-MM}/kafka-consumer-%d{yyyy-MM-dd}-%i.log.gz" -Drollover.files=$rollOverFiles -DlogLevel=$logLevel -Dserver.port=$server_port -Xmx2048m -jar $currentDir/sites_creation.jar  > $logging_dir/out.txt 2>$logging_dir/err.txt  &
echo $! > $logging_dir/pid.txt

if [ ! -f $logging_dir/$logFileName ]
	then
        echo "Waiting for file to get created"
        sleep 5
else
	echo "Log file present, displaying"
fi
sleep 5
timeout 30 tail -f $logging_dir/$logFileName

#Todo add condition for server up - also needs work in java.


