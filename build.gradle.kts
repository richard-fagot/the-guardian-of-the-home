import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent.*

plugins {
    application
    kotlin("jvm") version "1.3.50" 
    `java-library`
}


application {
    mainClassName = "home.guardian.Main"
}

repositories {
    jcenter() 
}

dependencies {
    implementation(kotlin("stdlib")) 
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.2.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.2.0")
    testRuntime("org.junit.platform:junit-platform-console:1.2.0")
    implementation("org.apache.httpcomponents:httpclient:4.5.6")
    implementation("org.apache.httpcomponents:httpcore:4.4.10")
    implementation("org.json:json:20180130")
    implementation("commons-logging:commons-logging:1.1.2")
}

tasks.test {
	useJUnitPlatform()
	testLogging {
		events = setOf(PASSED, SKIPPED, FAILED)
        exceptionFormat = TestExceptionFormat.FULL
        showExceptions = true
        showCauses = true
        showStackTraces = true
	}
}
