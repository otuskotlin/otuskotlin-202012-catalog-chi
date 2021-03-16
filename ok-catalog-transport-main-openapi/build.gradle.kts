plugins {
    kotlin("jvm")
    id("org.openapi.generator")
}

group = rootProject.group
version = rootProject.version

repositories {
    mavenCentral()
}

//зависимости у каждого модуля - свои
dependencies {
    val kotestVersion: String by project
    val openapiVersion: String by project
    val kotlinVersion: String by project
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
    implementation("com.squareup.moshi:moshi-kotlin:1.9.2")
    implementation("com.squareup.moshi:moshi-adapters:1.9.2")
    implementation("com.squareup.okhttp3:okhttp:4.2.2")

    //testImplementation(kotlin("test"))
    //в одноплатформенных проектах рекомендуется использовать Junit 5
    //отличия в аннотациях и наличие параметризованных тестов
    //в mp с ним может быть камасутра
    //Junit 5 begin
    testImplementation(kotlin("test-junit5"))
    testImplementation(platform("org.junit:junit-bom:5.7.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    //Junit 5 end

    //kottest begin
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
    testImplementation("io.kotest:kotest-property:$kotestVersion")
    //kottest end
}

//Junit 5 begin
tasks {
    test {
        useJUnitPlatform()
    }
}
//Junit 5 end

openApiGenerate {
    val basePackage = "${project.group}.transport.main.openapi"
    packageName.set(basePackage)
    generatorName.set("kotlin-server") //kotlin, kotlinserver
    configOptions.apply { //много параметров, см. доку
        //put("library", "jvm-okhttp4")
        put("library", "ktor")
        //put("requestDateConverter", "toString")
    }

    //inputSpec.set("${rootProject.projectDir}/specs/catalog-category-api.yaml")
    inputSpec.set("${rootProject.projectDir}/specs/catalog-classification-api.yaml")
    //inputSpec.set("${rootProject.projectDir}/specs/catalog-demand-api.yaml")
    //inputSpec.set("${rootProject.projectDir}/specs/marketplace-demand-api.yaml")
    //inputSpec.set("${rootProject.projectDir}/specs/catalog-api.yaml")
    //inputSpec.set("${rootProject.projectDir}/specs/pet-shop.yaml")

}
openApiValidate {
    val basePackage = "${project.group}.transport.main.openapi"
    inputSpec.set("${rootProject.projectDir}/specs/catalog-api.yaml")
    //inputSpec.set("${rootProject.projectDir}/specs/pet-shop.yaml")
}

sourceSets.main {
    java.srcDirs("$buildDir/generate-resources/main/src/main/kotlin")
}

tasks {
    compileKotlin.get().dependsOn(openApiGenerate)
}
