buildscript {
    dependencies {
        classpath("org.postgresql:postgresql:42.7.10")
        classpath("org.flywaydb:flyway-database-postgresql:11.14.1")
    }
}

plugins {
    java
    id("org.springframework.boot") version "4.0.3"
    id("io.spring.dependency-management") version "1.1.7"
    id("org.jooq.jooq-codegen-gradle") version "3.19.30"
    id("org.flywaydb.flyway") version "11.14.1"
}

group = "com.xaxage"
version = "0.0.1-SNAPSHOT"
description = "jobapp"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

repositories {
    mavenCentral()
}


dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webmvc")
    implementation("com.github.f4b6a3:uuid-creator:6.1.1")

    implementation("org.mapstruct:mapstruct:1.5.5.Final")
    implementation("org.springframework.boot:spring-boot-starter-flyway")
    implementation("org.flywaydb:flyway-database-postgresql")
    implementation("org.springframework.boot:spring-boot-starter-jooq")
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")

    runtimeOnly("org.postgresql:postgresql")
    jooqCodegen("org.postgresql:postgresql")
}

tasks.named("jooqCodegen") {
    doFirst {
        System.setProperty("user.timezone", "UTC")
    }
}

flyway {
    url = property("db.url") as String
    user = property("db.user") as String
    password = property("db.password") as String
    driver = "org.postgresql.Driver"
}

jooq {
    configuration {
        jdbc {
            driver = "org.postgresql.Driver"
            url = property("db.url") as String
            user = property("db.user") as String
            password = property("db.password") as String
            properties {
                property {
                    key = "options"
                    value = "-c TimeZone=UTC"
                }
            }
        }
        generator {
            database {
                name = "org.jooq.meta.postgres.PostgresDatabase"
                inputSchema = "public"
                excludes = "flyway_schema_history"
            }
            generate {
                isRecords = true
                isPojos = false
            }
            target {
                packageName = "com.xaxage.jobapp.generated"
                directory = "src/main/java"
            }
        }
    }
}
