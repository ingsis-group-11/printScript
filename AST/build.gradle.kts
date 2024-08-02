plugins {
    id("java")
}

group = "org.example"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(mapOf("path" to ":token")))
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}