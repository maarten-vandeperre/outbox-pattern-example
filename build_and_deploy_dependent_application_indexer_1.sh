#!/bin/sh
VERSION=0.0.5

./gradlew :applications:dependent-application-indexer-1:build -Dquarkus.package.type=uber-jar
docker build -t quay.io/appdev_playground/outbox-pattern-example:dependent-application-indexer-1-$VERSION -f applications/dependent-application-indexer-1/metadata/Dockerfile applications/dependent-application-indexer-1 --platform linux/amd64
docker push quay.io/appdev_playground/outbox-pattern-example:dependent-application-indexer-1-$VERSION

oc apply -f applications/dependent-application-indexer-1/metadata/knative_serving_config.yaml