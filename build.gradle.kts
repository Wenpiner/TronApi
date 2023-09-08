repositories {
    mavenCentral()
}
tasks.bootJar {
    enabled = false
}

tasks.jar {
    enabled = true
}

dependencies {
    implementation("com.google.code.gson:gson:2.8.9")
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation("cn.hutool:hutool-crypto:5.8.21")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
}