val ktorVersion: String by project
val kotlinVersion: String by project
val logbackVersion: String by project

plugins {
    application
    kotlin("jvm")
    id("com.bmuschko.docker-java-application")
}

group = rootProject.group
version = rootProject.version

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

docker {
//  это настройки для отправки в реестр
//  url = 'https://192.168.59.103:2376'
//  certPath = new File(System.properties['user.home'], '.boot2docker/certs/boot2docker-vm')

//    registryCredentials {
//        url.set(dockerParams.dockerUrl)
//        dockerParams.dockerUser?.also { username.set(it) }
//        dockerParams.dockerPass?.also { password.set(it) }
//    email = 'benjamin.muschko@gmail.com'
//    }

    javaApplication {
        baseImage.set("adoptopenjdk/openjdk11:alpine-jre")
        maintainer.set("(c) Chia")
        ports.set(listOf(8081))
        val imageName = project.name
        images.set(listOf(
            "$imageName:${project.version}",        //создается два образа, текущая верисия
            "$imageName:latest"                     //последняя версия
        ))
        jvmArgs.set(listOf("-Xms256m", "-Xmx512m"))
    }
}

repositories {
    mavenLocal()
    jcenter()
    maven { url = uri("https://kotlin.bintray.com/ktor") }
}

dependencies {
    implementation(project(":ok-catalog-be-common"))
    implementation(project(":ok-catalog-transport-main-mp"))
    implementation(project(":ok-catalog-transport-mapper"))
    implementation(project(":ok-catalog-be-business-logic"))

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-server-host-common:$ktorVersion")
    implementation("io.ktor:ktor-serialization:$ktorVersion")
    implementation("io.ktor:ktor-html-builder:$ktorVersion")
    testImplementation("io.ktor:ktor-server-tests:$ktorVersion")
}
