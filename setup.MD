# Environment setup
## Postgres (master database)
```shell
 oc new-app \
  -e POSTGRES_USER=admin \
  -e POSTGRES_PASSWORD=quarkus \
  -e POSTGRES_DB=master_database \
  -e PGDATA=/tmp/data/pgdata \
  quay.io/appdev_playground/wal_postgres:0.0.2 \
  --name postgres
```

```shell
oc port-forward postgres-867c649f57-gf6qm  5432:5433
```

```postgresql
CREATE TABLE person (
    id serial PRIMARY KEY,
    firstName VARCHAR ( 255 ),
    lastName VARCHAR ( 255 ),
    birthDate VARCHAR ( 50 )
);
CREATE SEQUENCE person_seq START 1;
```

## MongoDB
```shell
oc new-app \
  -e MONGO_INITDB_ROOT_USERNAME=admin \
  -e MONGO_INITDB_ROOT_PASSWORD=quarkus \
  mongo:4.2.24 \
  --name mongodb
```

## Setup AMQ Streams and Debezium
* Create push secret 
    ```shell
    oc create secret generic \
    kafka-connect-cluster-push-secret \
    --from-file=.dockerconfigjson=/Users/maartenvandeperre/Downloads/rh-ee-mvandepe-auth.json  \
    --type=kubernetes.io/dockerconfigjson
    ```
  See: https://github.com/maarten-vandeperre/knative-serverless-example-workshop/blob/main/workshop/wrap_up_operator_config.MD
  for more information
* Install operator
* Install Kafka instance 'my-cluster' & wait until condition 'Ready'
* Install Kafka Connect (via config in metadata folder) & wait until condition 'Ready'
    ```shell
    oc apply -f metadata/kafka_connect_cluster.yaml
    ```
  This step can take a while as it is getting built. If you would like to see the logs,
    check the build section.
* ```shell
    oc apply -f metadata/debezium_postgres_connector.yaml
    ```
* Test messages on topic after REST POST on /people endpoint
    ```shell
    oc exec -it my-cluster-kafka-0 \
    -- bin/kafka-console-consumer.sh \
    --bootstrap-server my-cluster-kafka-bootstrap.maarten-kafka.svc.cluster.local:9092 \
    --topic master_database_data_changed.public.person
    ```