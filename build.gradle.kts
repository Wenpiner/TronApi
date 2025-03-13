plugins {
    kotlin("jvm") version "2.1.0"
}

group = "com.github.wenpiner"
version = "1.0.1"

repositories {
    mavenCentral()
}

tasks.jar {
    enabled = true
    manifest {
        // Implementation-Title
        attributes["Implementation-Title"] = "TronApi"
        // Implementation-Version
        attributes["Implementation-Version"] = version
    }
}

dependencies {
    implementation("com.google.code.gson:gson:2.8.9")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("cn.hutool:hutool-crypto:5.8.36")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
}

tasks.test {
    useJUnitPlatform()
}