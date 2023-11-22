package com.redhat.demo

import io.quarkus.hibernate.orm.panache.PanacheEntity
import io.quarkus.hibernate.orm.panache.PanacheRepository
import io.quarkus.panache.common.Sort
import jakarta.enterprise.context.ApplicationScoped
import jakarta.persistence.Entity

@ApplicationScoped
class PostgresPersonRepository : PanacheRepository<Person> {
    fun all(): List<Person> = findAll(Sort.by("lastName")).list()
}

@Entity
data class Person(
    var firstName: String? = null,
    var lastName: String? = null,
    var birthDate: String? = null
) : PanacheEntity()