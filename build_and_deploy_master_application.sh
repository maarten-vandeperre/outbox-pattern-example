#!/bin/sh
VERSION=0.0.2

./gradlew :applications:master-application:build -Dquarkus.package.type=uber-jar
docker build -t quay.io/appdev_playground/outbox-pattern-example:master-application-$VERSION -f applications/master-application/metadata/Dockerfile applications/master-application --platform linux/amd64
docker push quay.io/appdev_playground/outbox-pattern-example:master-application-$VERSION

oc apply -f applications/master-application/metadata/deployment_config.yaml
oc apply -f applications/master-application/metadata/8080_service_config.yaml
oc apply -f applications/master-application/metadata/8080_route_config.yaml