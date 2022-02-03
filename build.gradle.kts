plugins {
    kotlin("jvm") version "1.6.10"
}

group = "info.puzan"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    api(platform("org.testcontainers:testcontainers-bom:1.16.3"))

    implementation(kotlin("stdlib-jdk8"))

    implementation("org.slf4j:slf4j-api:1.7.35")
    implementation("org.slf4j:slf4j-simple:1.7.35")

    testImplementation(group = "org.testcontainers", name = "mockserver")
    testImplementation(group = "org.mock-server", name = "mockserver-client-java", version = "5.11.2")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
}

tasks.withType<Test> {
    useJUnitPlatform()
}