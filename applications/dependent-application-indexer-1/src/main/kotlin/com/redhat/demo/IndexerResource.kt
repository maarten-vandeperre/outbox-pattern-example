package com.redhat.demo

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response

@Path("/indexer")
class IndexerResource(
    private val postgresPersonRepository: PostgresPersonRepository,
    private val mongoPersonRepository: MongoPersonRepository
) {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    fun index(): Response {
        val indexedPeople = postgresPersonRepository.all()
            .map {
                MongoPerson(
                    firstName = it.firstName,
                    lastName = it.lastName,
                    fullName = "${it.firstName} ${it.lastName} (${it.id}) - by reindex",
                    birthDate = it.birthDate
                )
            }
        mongoPersonRepository.deleteAll()
        mongoPersonRepository.persist(indexedPeople)
        return Response.ok("ended").build()
    }
}