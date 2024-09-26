#!/bin/bash

### log file backup
if [ -f nohup.out ]; then
    mv nohup.out "nohup_$TIMESTAMP.out"
    echo "Renamed nohup.out to nohup_$TIMESTAMP.out"
else
    echo "nohup.out not found"
fi

### kill process
PID=$(ps aux | grep 'ourJourney-0.0.1-SNAPSHOT.jar' | grep -v grep | awk '{print $2}')

if [ -n "$PID" ]; then
    echo "Killing process $PID"
    kill $PID
else
    echo "Process not found"
fi

### start process
export $(cat .env.production | xargs)
nohup java -Dspring.profiles.active=production -jar ourJourney-0.0.1-SNAPSHOT.jar &
