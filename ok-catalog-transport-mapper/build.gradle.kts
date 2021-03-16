plugins {
    kotlin("jvm")
}

group = rootProject.group
version = rootProject.version

repositories {
    mavenCentral()
}

//зависимости у каждого модуля - свои
dependencies {
    val kotestVersion: String by project
    implementation(kotlin("stdlib"))
    implementation(project(":ok-catalog-be-common"))
    implementation(project(":ok-catalog-transport-main-mp"))

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

