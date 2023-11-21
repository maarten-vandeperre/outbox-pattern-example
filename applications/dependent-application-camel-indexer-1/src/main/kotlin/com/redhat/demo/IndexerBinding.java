package com.redhat.demo;


//kamel run applications/dependent-application-camel-indexer-1/src/main/kotlin/com/redhat/demo/IndexerBinding.java --dev

// kamel run JDBCSelect.java --dev --config secret:my-datasource

// camel-k: dependency=camel:jdbc
// camel-k: dependency=mvn:io.quarkus:quarkus-jdbc-postgresql

import org.apache.camel.builder.RouteBuilder;

public class IndexerBinding extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("timer://foo?period=10")
                .setBody(constant("SELECT data FROM test LIMIT 5 OFFSET 0"))
                .to("jdbc:default")
                .to("log:info");
    }
}
