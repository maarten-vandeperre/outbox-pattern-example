package com.redhat.demo

import io.quarkus.mongodb.panache.PanacheMongoEntity
import io.quarkus.mongodb.panache.PanacheMongoRepository
import io.quarkus.mongodb.panache.common.MongoEntity
import io.quarkus.panache.common.Sort
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class PersonRepository : PanacheMongoRepository<Person> {
    fun all(): List<Person> = findAll(Sort.by("lastName")).list()
}

@MongoEntity(collection = "people")
data class Person(
    var firstName: String? = null,
    var lastName: String? = null,
    var fullName: String? = null,
    var birthDate: String? = null
) : PanacheMongoEntity()