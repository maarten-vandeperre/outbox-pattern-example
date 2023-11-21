package com.redhat.demo

import jakarta.transaction.Transactional
import jakarta.ws.rs.*
import jakarta.ws.rs.core.Response

@Path("/people")
class PersonResource(
    private val personRepository: PersonRepository
) {

    @GET
    fun getAll(): Response {
        return Response.ok(personRepository.all().toList().joinToString(",")).build()
    }

    @POST
    @Transactional
    fun create(@FormParam("firstName") firstName: String, @FormParam("lastName") lastName: String, @FormParam("birthDate") birthDate: String): Response {
        personRepository.persist(Person(firstName = firstName, lastName = lastName, birthDate = birthDate))
        return Response.noContent().build()
    }
}