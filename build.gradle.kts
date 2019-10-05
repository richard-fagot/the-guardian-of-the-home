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
}

