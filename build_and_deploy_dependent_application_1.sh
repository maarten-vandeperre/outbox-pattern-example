#!/bin/sh
VERSION=0.0.13

./gradlew :applications:dependent-application-1:build -Dquarkus.package.type=uber-jar
docker build -t quay.io/appdev_playground/outbox-pattern-example:dependent-application-1-$VERSION -f applications/dependent-application-1/metadata/Dockerfile applications/dependent-application-1 --platform linux/amd64
docker push quay.io/appdev_playground/outbox-pattern-example:dependent-application-1-$VERSION

oc apply -f applications/dependent-application-1/metadata/deployment_config.yaml
oc apply -f applications/dependent-application-1/metadata/8080_service_config.yaml
oc apply -f applications/dependent-application-1/metadata/8080_route_config.yaml