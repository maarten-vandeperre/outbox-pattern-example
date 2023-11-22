package com.redhat.demo;


//kamel run applications/dependent-application-camel-indexer-1/src/main/kotlin/com/redhat/demo/IndexerBinding.java --dev

// kamel init applications/dependent-application-camel-indexer-1/src/main/kotlin/com/redhat/demo/IndexerBinding2.java
// kamel run applications/dependent-application-camel-indexer-1/src/main/kotlin/com/redhat/demo/IndexerBinding2.java
// kamel run JDBCSelect.java --dev --config secret:my-datasource

// camel-k: dependency=camel:jdbc
// camel-k: dependency=mvn:io.quarkus:quarkus-jdbc-postgresql

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

public class IndexerBinding extends RouteBuilder {
    @Override
    public void configure() throws Exception {
//        from("timer://foo?period=10")
//                .to("direct:print-something")
//                .setBody(constant("SELECT data FROM test LIMIT 5 OFFSET 0"))
//                .to("jdbc:default")
//                .to("log:info")
//        ;

        from("direct:start")
                .bean(this, "process")
                .to("log:info")
                ;
    }

    public String process(Exchange exchange){
        System.out.println("processing");
        return "ok";
    }
}
