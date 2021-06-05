#!/bin/bash
docker-compose -f ./portainer/docker-compose.yml -f ./mysql/docker-compose.yml down --remove-orphans