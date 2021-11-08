#!/bin/sh
#We compulsorily need the parameters server.port, profile, and log level
#Optional parameters are hostname

#Take command line argument -server_port
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

#as it is being deployed on heroku so
logging_dir=./logs/$instanceId







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

nohup java -Dspring.cloud.config.uri=http://localhost:8085  -Dspring.profiles.active=$profile  -Dmax.file.size="$sizeOfLogFile" -DlogLevel=$logLevel -Dlog.file.name="$logging_dir/$logFileName" -Dlog.file.pattern="$logging_dir/\$\${date:yyyy-MM}/hacker-bills-%d{yyyy-MM-dd}-%i.log.gz" -Drollover.files=$rollOverFiles -Dserver.port=$serverPort -Xmx2048m -jar ./build/libs/hacker_bills.jar > $logging_dir/out.txt 2>$logging_dir/err.txt  &
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




