#!/bin/bash

PID=$(ps aux | grep 'ourJourney-0.0.1-SNAPSHOT.jar' | grep -v grep | awk '{print $2}')

if [ -n "$PID" ]; then
    echo "Killing process $PID"
    kill $PID
else
    echo "Process not found."
fi

export $(cat .env.production | xargs)

nohup java -Dspring.profiles.active=production -jar ourJourney-0.0.1-SNAPSHOT.jar &
