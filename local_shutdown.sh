#!/bin/sh
#We compulsorily need the parameters listener.port , server.port, and device.type
#Optional parameters are hostname

#Take command line argument -listener_Port
#Do validations, there should be at least 3 arguments





echo "Graceful shutdown is not currently implemented... going to stop via kill -9"
#Todo - attempt shutdown via actuator's shutdown url, and wait.


instance_id=1
logRoot="/var/log/javaapps/sites-creation"

logging_dir=$logRoot/$instance_id
pid=`cat $logging_dir/pid.txt`

echo "Process pid: $pid"

if ! [ "$pid" -eq "$pid" ] 2>/dev/null
then
	echo "Could not find pid"
	exit 2
fi

kill -9 $pid
if [ $? -eq 0 ]
then
	echo "Killed process $pid successfully"
	mv $logging_dir/pid.txt $logging_dir/pid.txt.bak
else
	echo "Could not kill process $pid"
	exit 3
fi


#Todo add condition for server up - also needs work in java.


