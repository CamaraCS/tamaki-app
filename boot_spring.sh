#!/bin/sh
set -e 

# ensure the following environment variables are set. exit script and container if not set.
# test $backend_host

/opt/confd/bin/confd -onetime -backend env -log-level DEBUG

echo "Starting Springboot"
exec java -jar data/app.jar --spring.profiles.active=default