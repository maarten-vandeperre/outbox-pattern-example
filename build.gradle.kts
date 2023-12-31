plugins {
    kotlin("jvm") version "1.9.10"
    kotlin("plugin.allopen") version "1.9.10"
    id("io.quarkus")
}

repositories {
    mavenCentral()
    mavenLocal()
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "io.quarkus")
    apply(plugin = "org.jetbrains.kotlin.plugin.allopen")

    val quarkusPlatformGroupId: String by project
    val quarkusPlatformArtifactId: String by project
    val quarkusPlatformVersion: String by project

    repositories {
        mavenCentral()
        mavenLocal()
    }

    dependencies {
        implementation(enforcedPlatform("${quarkusPlatformGroupId}:${quarkusPlatformArtifactId}:${quarkusPlatformVersion}"))
        implementation(enforcedPlatform("${quarkusPlatformGroupId}:quarkus-camel-bom:${quarkusPlatformVersion}"))
        implementation("io.quarkus:quarkus-resteasy-reactive-jackson")
        implementation("io.quarkus:quarkus-resteasy-reactive")
        implementation("io.quarkus:quarkus-kotlin")
        implementation("io.quarkus:quarkus-resteasy-reactive-kotlin-serialization")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        implementation("io.quarkus:quarkus-arc")
        testImplementation("io.quarkus:quarkus-junit5")
        testImplementation("io.rest-assured:rest-assured")
    }

    group = "com.redhat.demo"
    version = "1.0.0-SNAPSHOT"

    java {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    tasks.withType<Test> {
        systemProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager")
    }

    allOpen {
        annotation("jakarta.ws.rs.Path")
        annotation("jakarta.enterprise.context.ApplicationScoped")
        annotation("io.quarkus.test.junit.QuarkusTest")
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = JavaVersion.VERSION_17.toString()
        kotlinOptions.javaParameters = true
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_17.toString()
    kotlinOptions.javaParameters = true
}
