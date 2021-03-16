plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
}

group = rootProject.group
version = rootProject.version

repositories {
    mavenCentral()
}

kotlin {
    /* Targets configuration omitted.
    *  To find out how to configure the targets, please follow the link:
    *  https://kotlinlang.org/docs/reference/building-mpp-with-gradle.html#setting-up-targets */

    js {
        browser {
            testTask {
                useKarma {
                    useChromeHeadless()
                }
            }
            binaries.executable()
        }
        nodejs {}
    }
    jvm {
        withJava()
    }




    sourceSets {
        //переменные определены в корневом gradle.properties
        //область видимости переменных делать максимально узкой
        val coroutinesVersion: String by project
        val serializationVersion: String by project
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                //это рекомендуемый вариант указания версий, наиболее лаконичный и устойчивый к опечаткам:
                api("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")
                //implementation("org.jetbrains.kotlinx:kotlinx-serialisation-json:$serializationVersion")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val jsMain by getting {
            dependencies {
                implementation(kotlin("stdlib-js"))
            }
        }
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(kotlin("stdlib"))
            }
        }
        val jvmTest by getting {
            dependencies {
                //это версия 4 JUnit
                implementation(kotlin("test-junit"))
            }
        }
    }
}
