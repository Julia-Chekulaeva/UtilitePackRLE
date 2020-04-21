package main.kotlin.PackRLE

import java.io.File
import java.nio.file.Paths
import kotlin.system.exitProcess

val regex = Regex("""pack-rle ((-z (-out [а-яА-Я\w\\/]+\.rle )?[а-яА-Я\w\\/]+\.txt)|(-u (-out [а-яА-Я\w\\/]+\.txt )?[а-яА-Я\w\\/]+\.rle))""")

class PackRLE(/*elems: Array<String>, */private val toRLE: Boolean, outputName: String?, inputName: String) {

    /*init {
        val s = elems.joinToString(" ") { it }
        if (!s.matches(regex)) {
            System.err.println("Утилита введена неправильно")
            exitProcess(-1)
        }
    }*/

    //private val inputName: String = elems.last()

    private val input: File = Paths.get(inputName).toFile()

    /*private val outputName: String =
            if (elems.size == 5) elems[3]
            else {
                if (elems[1] == "-z") elems[2].removeSuffix("txt") + "rle"
                else elems[2].removeSuffix("rle") + "txt"
            }*/

    private val output: File = Paths.get(
        outputName ?: if (toRLE) inputName.removeSuffix("txt") + "rle"
        else inputName.removeSuffix("rle") + "txt"
    ).toFile()

    //private val toRLE = elems[1] == "-z"

    fun utilite(): Unit {
        try {
            val file = input.readText()
            var i = 0
            var count: Int
            val lastIndex = file.lastIndex
            output.bufferedWriter().use {
                if (toRLE) {
                    while (i < lastIndex) {
                        count = 1
                        while (i < lastIndex && file[i + 1] == file[i]) {
                            count++
                            i++
                        }
                        val needRLE = count > 4 || file[i] == '/' && count > 1
                        val c = if (file[i] == '/' && count == 1) "//" else file[i].toString()
                        if (needRLE)
                            it.write("/$count/$c")
                        else
                            for (j in 0 until count)
                                it.write(c)
                        i++
                    }
                    if (i == lastIndex) it.write(file[i].toString())
                } else {
                    while (i < lastIndex) {
                        if (file[i] == '/') {
                            i++
                            when (file[i]) {
                                '/' -> it.write("/")
                                in '0'..'9' -> {
                                    count = 0
                                    while (i < lastIndex && file[i] in '0'..'9') {
                                        count = count * 10 + (file[i] - '0')
                                        i++
                                    }
                                    if (file[i] == '/' && i < lastIndex)
                                        i++
                                    else {
                                        System.err.println("Недопустимый формат rle-файла")
                                        exitProcess(-1)
                                    }
                                    val needToWrite = StringBuilder()
                                    for (j in 0 until count) needToWrite.append(file[i])
                                    it.write(needToWrite.toString())
                                }
                                else -> {
                                    System.err.println("Недопустимый формат rle-файла")
                                    exitProcess(-1)
                                }
                            }
                        } else it.write(file[i].toString())
                        i++
                    }
                    if (i == lastIndex) it.write(file[i].toString())
                }
            }
        } catch (e: java.io.FileNotFoundException) {
            System.err.println("Файл не найден")
            exitProcess(-1)
        }
    }

}