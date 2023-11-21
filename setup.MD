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