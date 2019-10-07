plugins {
    application
    kotlin("jvm") version "1.3.50" 
}


application {
    mainClassName = "home.guardian.TestKt"
}

repositories {
    jcenter() 
}

dependencies {
    implementation(kotlin("stdlib")) 
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.2.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.2.0")
    testRuntime("org.junit.platform:junit-platform-console:1.2.0")
}

tasks {
    // Use the native JUnit support of Gradle.
    "test"(Test::class) {
        useJUnitPlatform()
    }
}