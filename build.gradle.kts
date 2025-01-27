plugins {
    application
    id("com.gradleup.shadow") version "8.3.1"
}

application.mainClass = "com.prism182.Main" //
group = "com.prism182"
version = "1.0"

val jdaVersion = "5.2.2" //

repositories {
    mavenCentral()
}

dependencies {
    implementation("net.dv8tion:JDA:$jdaVersion")
    implementation("ch.qos.logback:logback-classic:1.5.6")
    implementation("io.github.cdimascio:dotenv-java:2.2.4")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.isIncremental = true

    // Set this to the version of java you want to use,
    // the minimum required for JDA is 1.8
    sourceCompatibility = "1.8"
}