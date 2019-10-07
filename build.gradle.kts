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
    api("junit:junit:4.12")
    implementation("junit:junit:4.12")
    testImplementation("junit:junit:4.12")
}

tasks {
    test {                                  
        useJUnitPlatform()
    }
}