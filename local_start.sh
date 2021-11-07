#!/bin/sh
#We compulsorily need the parameters listener.port , server.port, and device.type
#Optional parameters are hostname

#Take command line argument -listener_Port
#Do validations, there should be at least 3 arguments

if [ $# -ne 3 ]
then
       echo "usage: ./$0 <server_port> <profile>  <logLevel>"
       exit 1
fi




serverPort=$1
profile=$2
logLevel=$3

instanceId=1
sizeOfLogFile="1024 MB"
rollOverFiles=1000
logging_dir=/var/log/javaapps/sites-creation/$instanceId







sh ./build.sh
buildSuccess=$?

if [ $buildSuccess -ne 0 ]
        then
                echo "Build failed, aborting!"
                exit 1
fi






# Logging_dir is for localhost.
mkdir -p $logging_dir
. /etc/profile

logFileName="hackers_bills.log"

echo -Dmax.file.size="$sizeOfLogFile"
#exit 0

nohup java -Dspring.cloud.config.uri=http://107.6.151.122:8888  -Dspring.profiles.active=$profile  -Dmax.file.size="$sizeOfLogFile" -DlogLevel=$logLevel -Dlog.file.name="$logging_dir/$logFileName" -Dlog.file.pattern="$logging_dir/\$\${date:yyyy-MM}/kafka-consumer-%d{yyyy-MM-dd}-%i.log.gz" -Drollover.files=$rollOverFiles -Dserver.port=$serverPort -Xmx2048m -jar ./build/libs/sites_creation.jar > $logging_dir/out.txt 2>$logging_dir/err.txt  &
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



