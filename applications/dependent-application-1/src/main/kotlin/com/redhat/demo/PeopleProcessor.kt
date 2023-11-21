package com.redhat.demo

import io.smallrye.reactive.messaging.annotations.Blocking
import jakarta.enterprise.context.ApplicationScoped
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.eclipse.microprofile.reactive.messaging.Incoming

@ApplicationScoped
class PeopleProcessor {
    @Incoming("people")
    @Blocking
    fun processPeopleData(dataRecord: ConsumerRecord<String, String>) {
        println("new data:\n${dataRecord.key()}\n\n${dataRecord.value()}")
    }
}