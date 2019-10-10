import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent.*
import org.jetbrains.kotlin.gradle.tasks.*

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
    // Librairie Kotlin standard
    implementation(kotlin("stdlib-jdk8")) 

    // Tests unitaires
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.2.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.2.0")
    testRuntime("org.junit.platform:junit-platform-console:1.2.0")

    // Appel Http
    implementation("org.apache.httpcomponents:httpclient:4.5.6")
    implementation("org.apache.httpcomponents:httpcore:4.4.10")
    implementation("org.json:json:20180130")
    implementation("commons-logging:commons-logging:1.1.2")

    // JSON-B 
    api("javax.json:javax.json-api:1.1")
    api("javax.json.bind:javax.json.bind-api:1.0")
    implementation("org.eclipse:yasson:1.0")
    implementation("org.glassfish:javax.json:1.1")
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

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
     }
}