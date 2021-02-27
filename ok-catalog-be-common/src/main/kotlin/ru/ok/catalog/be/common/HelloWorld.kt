package ru.ok.catalog.be.common

fun main() {
    println(helloMessage("Hello", "world"))

    println(helloMessage("Привет", "Илья"))
}

fun helloMessage(greeting: String, subject: String): String {
    return "$greeting, $subject!"
}