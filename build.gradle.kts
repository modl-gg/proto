plugins {
    `java-library`
    `maven-publish`
}

group = "gg.modl"
version = "1.0.0"

repositories {
    mavenCentral()
}

sourceSets {
    main {
        java {
            srcDir("gen/java")
        }
    }
}

tasks.withType<JavaCompile> {
    options.release.set(8)
}

dependencies {
    api("com.google.protobuf:protobuf-java:4.34.1")
    api("com.google.protobuf:protobuf-java-util:4.34.1")
    api("build.buf:protovalidate:0.4.1")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
            artifactId = "modl-proto"
        }
    }
    repositories {
        maven {
            name = "ModlNexus"
            url = uri("https://nexus.modl.gg/repository/maven-releases/")
            credentials {
                username = System.getenv("NEXUS_USER") ?: findProperty("nexus.user") as String?
                password = System.getenv("NEXUS_PASS") ?: findProperty("nexus.pass") as String?
            }
        }
    }
}
