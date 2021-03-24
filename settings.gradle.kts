rootProject.name = "ok-catalog"

pluginManagement {
    //этот блок позволяет указать версии из gradle.properties
    //указание версий необходимо для единой политики применения версий плагинов во всех подпроектах
    val kotlinVersion: String by settings
    val openapiVersion: String by settings
    val bmuschkoVersion: String by settings

    plugins {
        //вынесено сюда в связи с тем, что на уровне build.gradle.kts
        //невозможно считывание версий из settings
        //в корневом build.gradle.kts plugins можно не перечислять,
        //но при определенных обстоятельствах возможны конфликты (т.е. как бы намек, что нужно перечислить)
        kotlin("multiplatform") version kotlinVersion
        kotlin("jvm") version kotlinVersion
        kotlin("js") version kotlinVersion
        kotlin("plugin.serialization") version kotlinVersion


        id("org.openapi.generator") version openapiVersion
        id("com.bmuschko.docker-java-application") version bmuschkoVersion
    }
}

include("ok-catalog-be-common")
include("ok-catalog-common-mp")
include("ok-catalog-transport-main-mp")
include("ok-catalog-transport-mapper")
include("ok-catalog-tools")
include("ok-catalog-transport-main-openapi")
include("ok-catalog-be-app-ktor")
