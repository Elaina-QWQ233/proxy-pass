plugins {
    id("java-library")
    alias(libs.plugins.lombok)
    `maven-publish`
}

group = "org.cloudburstmc.proxypass"
version = libs.versions.packaging.get()

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://repo.opencollab.dev/maven-releases")
    maven("https://repo.opencollab.dev/maven-snapshots")
}

dependencies {
    api(project(":network:transport-raknet"))
    api(project(":protocol:bedrock-codec"))
    api(project(":protocol:bedrock-connection"))
    api(project(":protocol:common"))
    api(libs.jackson.databind)
    api(libs.jackson.dataformat.yaml)
    api(libs.net.raphimc.minecraftauth)
    api(platform(libs.log4j.bom))
    api(libs.log4j.api)
    api(libs.log4j.core)
//    compileOnly(libs.lombok)
//    annotationProcessor(libs.lombok)
//    compileOnly(libs.jsr305)
//    compileOnly(libs.checker.qual)
//    implementation(libs.bedrock.codec)
//    implementation(libs.bedrock.common)
//    implementation(libs.bedrock.connection)
//    implementation(libs.jackson.databind)
//    implementation(libs.jackson.dataformat.yaml)
//    implementation(libs.common)
//    implementation(libs.jansi)
//    implementation(libs.jline.reader)
//    implementation(libs.minecraftauth)
}

tasks.create<Jar>("sourceJar") {
    from(sourceSets["main"].allSource)
    archiveClassifier = "sources"
}

publishing {
    publications {
        create<MavenPublication>("App") {
            from(components["java"])
            artifact(tasks["sourceJar"])
        }
    }
}