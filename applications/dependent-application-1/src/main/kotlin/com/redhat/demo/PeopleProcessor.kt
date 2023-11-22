package com.redhat.demo

import com.fasterxml.jackson.databind.ObjectMapper
import io.quarkus.mongodb.panache.PanacheMongoRepository
import io.smallrye.reactive.messaging.annotations.Blocking
import jakarta.enterprise.context.ApplicationScoped
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.eclipse.microprofile.reactive.messaging.Incoming

@ApplicationScoped
class PeopleProcessor(
    private val personRepository: PersonRepository
) {
    private val mapper = ObjectMapper()

    @Incoming("people")
    @Blocking
    fun processPeopleData(dataRecord: ConsumerRecord<String, String>) {
        println("new data:\n${dataRecord.key()}\n\n${dataRecord.value()}")
        if(dataRecord.value() != null){
            val json = mapper.readTree(dataRecord.value())
            if(!json.get("payload").get("after").isNull){
                val payload = json.get("payload").get("after")
                Person(
                    firstName = payload.get("firstname").asText(),
                    lastName = payload.get("lastname").asText(),
                    fullName = "${payload.get("firstname").asText()} ${payload.get("lastname").asText()} (${payload.get("id").asText()})",
                    birthDate = payload.get("birthdate").asText()
                ).persist()
            } else {
                val id = json.get("payload").get("id")
                //TODO remove for id
                personRepository.deleteAll() //TODO - by id
            }
        }
    }
}