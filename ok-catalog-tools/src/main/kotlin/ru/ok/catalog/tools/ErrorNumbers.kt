package ru.ok.catalog.tools

import java.io.File

fun main() {
    val re = Regex(".+\\.kt$");
    var maxen = 0
    val fileToProcess: MutableList<String> = mutableListOf();
    var totalProcessed: Int = 0
    val totalRenumber = false


    fun scanFile(fileName: String): Int {
        val re = Regex("\"MP-E-(\\d*)");
        var res = 0
        var firstTime = true
        File(fileName).forEachLine {
            val match = re.find(it)
            if (match != null) {
                val (num) = match.destructured
                if ( num.isNotBlank() ) {
                    if ( num.toInt() > maxen )
                        maxen = num.toInt()
                    if ( totalRenumber )
                        res = 1
                } else {
                    res = 1
                }
                if ( firstTime ) {
                    println(fileName)
                    firstTime = false
                }
                println(it)
            }
        }

        return res
    }

    fun processFile(fileName: String): Int {
        val re = Regex("\"MP-E-(\\d*)");
        var res = 0
        var nl: String = ""
        val out = File("$fileName.tmp").printWriter();
        var processed = 0
        File(fileName).forEachLine {
            val match = re.find(it)
            if (match != null) {
                res = 1
                val (num) = match.destructured
                if ( num.isBlank() || totalRenumber ) {
                    nl = re.replaceFirst(it,"\"MP-E-"+"%04d".format(maxen++))
                    println(it)
                    println(nl)
                    out.println(nl)
                    totalProcessed++
                    processed++
                } else {
                    out.println(it)
                }
                //println(num)
            } else {
                out.println(it)
            }
        }
        out.close()
        if ( processed > 0 ) {
            File("$fileName.bak2").delete()
            File(fileName).renameTo(File("$fileName.bak2"))
            File("$fileName.tmp").renameTo(File(fileName))
        }

        return res
    }

    println("ErrorNumbers - присвоение и перенумерация кодов ошибок")
    println("Сканирование файлов...")
    File(".").walkTopDown().forEach {
        //println(it.name)
        if ( it.isFile && it.name != "ErrorNumbers.kt" && re.matches(it.name)) {
            val rc = scanFile(it.toString())
            if ( rc > 0 ) {
                fileToProcess.add(it.toString())
                println(it)
            }
        }
    }
    if ( totalRenumber )
        maxen = 1
    else
        maxen++
    println("Следующий свободный номер: $maxen")
    println("Файлов для обработки: ${fileToProcess.size}")

    if ( fileToProcess.size > 0 ) {
        println("Присвоение номеров...")
        fileToProcess.forEach {
            println(it)
            processFile(it)
        }
        println("Присвоено номеров: $totalProcessed")
        println("Следующий свободный номер: $maxen")
    }
}


