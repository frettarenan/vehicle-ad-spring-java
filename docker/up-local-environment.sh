#!/bin/bash
docker-compose -f ./portainer/docker-compose.yml -f ./mysql/docker-compose.yml up -d rf-portainer rf-mysql-vehicle-ad