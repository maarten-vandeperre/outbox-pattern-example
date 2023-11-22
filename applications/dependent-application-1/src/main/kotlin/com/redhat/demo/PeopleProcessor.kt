package com.redhat.demo

import com.fasterxml.jackson.databind.ObjectMapper
import io.smallrye.reactive.messaging.annotations.Blocking
import jakarta.enterprise.context.ApplicationScoped
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.eclipse.microprofile.reactive.messaging.Incoming

@ApplicationScoped
class PeopleProcessor{
    private val mapper = ObjectMapper()

    @Incoming("people")
    @Blocking
    fun processPeopleData(dataRecord: ConsumerRecord<String, String>) {
        println("new data:\n${dataRecord.key()}\n\n${dataRecord.value()}")
        val json = mapper.readTree(dataRecord.value())
        val payload = json.get("payload").get("after")
        Person(
            firstName = payload.get("firstname").asText(),
            lastName = payload.get("lastname").asText(),
            fullName = "${payload.get("firstname").asText()} ${payload.get("lastname").asText()} (${payload.get("id").asText()})",
            birthDate = payload.get("birthdate").asText()
        ).persist()
    }
}