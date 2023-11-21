package com.redhat.demo

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response

@Path("/indexer")
class IndexerResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    fun index(): Response {
        return Response.ok("started").build()
    }
}