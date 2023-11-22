package com.redhat.demo

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
}