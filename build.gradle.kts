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
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.isIncremental = true

    // Set this to the version of java you want to use,
    // the minimum required for JDA is 1.8
    sourceCompatibility = "1.8"
}