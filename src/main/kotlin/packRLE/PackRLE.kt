package packRLE

import java.io.File
import java.lang.IndexOutOfBoundsException
import java.nio.file.Paths
import kotlin.system.exitProcess

const val MAX_REPEAT_COUNT = 32

const val MAX_USUAL_COUNT = Byte.MAX_VALUE - MAX_REPEAT_COUNT - Byte.MIN_VALUE

const val ZERO_FOR_USUAL = Byte.MIN_VALUE

const val ZERO_FOR_REPEAT = Byte.MAX_VALUE - MAX_REPEAT_COUNT + 1

val regexRLE = Regex("""[а-яА-Я\w\W\d_\\/]+\.rle""")

val regexTXT = Regex("""[а-яА-Я\w\W\d_\\/]+\.txt""")

class PackRLE(private val toRLE: Boolean, outputName: String?, inputName: String) {

    init {
        if (toRLE) {
            if (!inputName.matches(regexTXT) || !(outputName ?: "a.rle").matches(regexRLE))
                error("Incorrect type of file(s) or converting")
        } else {
            if (!inputName.matches(regexRLE) || !(outputName ?: "a.txt").matches(regexTXT))
                error("Incorrect type of file(s) or converting")
        }
    }

    private val input: File = Paths.get(inputName).toFile()

    private val output: File = Paths.get(
        outputName ?: if (toRLE) inputName.removeSuffix("txt") + "rle"
        else inputName.removeSuffix("rle") + "txt"
    ).toFile()

    fun utilite() {
        try {
            val file = input.readBytes()
            println(file.size)
            var i = 0
            val list = mutableListOf<Byte>()
            val res = mutableListOf<Byte>()
            val lastIndex = file.lastIndex
            if (toRLE) {
                var usualCount = 0
                var repeatCount: Int
                while (i < lastIndex) {
                    repeatCount = 1
                    while (i < lastIndex && file[i + 1] != file[i] && usualCount < MAX_USUAL_COUNT) {
                        list.add(file[i])
                        usualCount++
                        i++
                    }
                    while (i < lastIndex && file[i + 1] == file[i] && repeatCount < MAX_REPEAT_COUNT) {
                        repeatCount++
                        i++
                    }
                    if (repeatCount > 2) {
                        // Записываем все накопившиеся
                        list.add(0, (usualCount + ZERO_FOR_USUAL).toByte())
                        res.addAll(list + (repeatCount + ZERO_FOR_REPEAT).toByte() + file[i])
                        // Обнуляем счетчик неповторяющихся символов
                        list.clear()
                        usualCount = 0
                    }
                    else {
                        for (k in 1..repeatCount) {
                            if (usualCount == MAX_USUAL_COUNT) {
                                //Записываем все накопившиеся
                                list.add(0, (usualCount + ZERO_FOR_USUAL).toByte())
                                res.addAll(list)
                                // Обнуляем счетчик неповторяющихся символов
                                list.clear()
                                usualCount = 0
                            }
                            list.add(file[i])
                            usualCount++
                        }
                    }
                    // Сдвигаем основной счетчик, т.к. в данный момент мы стоим на символе, который уже записали
                    i++
                }
                if (i == lastIndex) {
                    list.add(file[i])
                    usualCount++
                }
                if (usualCount > 0) {
                    //Записываем все накопившиеся
                    list.add(0, (usualCount + ZERO_FOR_USUAL).toByte())
                    res.addAll(list)
                }
                output.writeBytes(res.toByteArray())
            } else {
                try {
                    while (i < lastIndex) {
                        if (file[i] < ZERO_FOR_REPEAT) {
                            for (j in 0 until file[i] - ZERO_FOR_USUAL) {
                                i++
                                res.add(file[i])
                            }
                            i++
                        } else {
                            for (j in 0 until file[i] - ZERO_FOR_REPEAT)
                                res.add(file[i + 1])
                            i += 2
                        }
                    }
                    if (i != lastIndex + 1) {
                        error("Ошибка при конвертации rle-файла")
                    }
                } catch (h: IndexOutOfBoundsException) {
                    error("Ошибка при конвертации rle-файла")
                }
                output.writeBytes(res.toByteArray())
            }
        } catch (e: java.io.FileNotFoundException) {
            error("Файл не найден")
        }
    }

}