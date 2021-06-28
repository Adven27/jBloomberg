import org.gradle.api.tasks.testing.logging.TestExceptionFormat.*
import org.gradle.api.tasks.testing.logging.TestLogEvent.*

plugins {
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    api(files("$rootDir/libs/blpapi-3.16.3-1.jar"))
    implementation("com.google.guava:guava:30.1.1-jre")
    implementation("org.slf4j:slf4j-api:1.7.25")
    testImplementation(project(":jbloomberg-test"))
    testImplementation("org.awaitility:awaitility:4.1.0")
    testImplementation("org.jmockit:jmockit:1.38")
    testImplementation("org.testng:testng:6.11")
}

tasks.test {
    useTestNG() {
        includeGroups = setOf("unit")
        excludeGroups = setOf("windows")
    }
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
