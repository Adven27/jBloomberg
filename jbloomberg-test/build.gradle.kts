import org.gradle.api.tasks.testing.logging.TestExceptionFormat.*
import org.gradle.api.tasks.testing.logging.TestLogEvent.*

plugins {
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":jbloomberg"))
    implementation("org.mockito:mockito-core:1.10.19")
    implementation("org.slf4j:slf4j-api:1.7.25")
}

tasks.test {
    useTestNG()
    testLogging {
        events(PASSED, FAILED, STANDARD_ERROR, SKIPPED)
        exceptionFormat = FULL
        showExceptions = true
        showCauses = true
        showStackTraces = true
        showStandardStreams = true
    }
}

apply(from = "$rootDir/gradle/publish.gradle")
