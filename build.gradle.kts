group = "ru.ok.catalog"
version = "0.0.1"

//так сразу для проекта и всех подпроектов:, TODO
//allprojects {
//    group = "ru.ok.catalog"
//    version = "0.0.1"
//}

//также можно здесь указать репозитории, TODO

plugins {
    //чтобы не было ошибок при сборке подпроектов
    //в случае, если плугины используются в нескольких
    //подпроектах, нужно описать
    //плугины здесь указав apply false
    kotlin("multiplatform") apply false
    kotlin("jvm") apply false
    kotlin("js") apply false
    kotlin("plugin.serialization") apply false
    id("org.openapi.generator") apply false
}


