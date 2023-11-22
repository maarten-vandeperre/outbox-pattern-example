// camel-k: language=java

import org.apache.camel.builder.RouteBuilder;

// kamel init applications/dependent-application-camel-indexer-1/src/main/kotlin/com/redhat/demo/IndexerBinding2.java
// kamel run applications/dependent-application-camel-indexer-1/src/main/kotlin/IndexerBinding2.java

public class IndexerBinding2 extends RouteBuilder {
  @Override
  public void configure() throws Exception {

      // Write your routes here, for example:
      from("timer:java?period=1000")
        .routeId("java")
        .setBody()
          .simple("Hello Camel K from ${routeId}")
        .to("log:info");

  }
}
