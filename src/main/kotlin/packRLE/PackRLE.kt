package packRLE

import java.io.File
import java.io.FileNotFoundException
import java.lang.IndexOutOfBoundsException
import java.nio.file.Paths

const val MAX_REPEAT_COUNT = 32

const val MAX_USUAL_COUNT = Byte.MAX_VALUE - MAX_REPEAT_COUNT - Byte.MIN_VALUE

const val ZERO_FOR_USUAL = Byte.MIN_VALUE

const val ZERO_FOR_REPEAT = Byte.MAX_VALUE - MAX_REPEAT_COUNT

val regexRLE = Regex("""[а-яА-Я\w\W\d_\\/]+\.rle""")

val regexTXT = Regex("""[а-яА-Я\w\W\d_\\/]+\.txt""")

class PackRLE(private val toRLE: Boolean, outputName: String?, inputName: String) {

    init {
        if (toRLE) {
            if (!inputName.matches(regexTXT) || !(outputName ?: "a.rle").matches(regexRLE))
                error("Неправильное расширение файла(ов) или тип конвертации")
        } else {
            if (!inputName.matches(regexRLE) || !(outputName ?: "a.txt").matches(regexTXT))
                error("Неправильное расширение файла(ов) или тип конвертации")
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

                // Я забыла пояснить эту деталь алгоритма. Если символ повторяется только 2 раза, то замена его
                // на строку вида 2р ничего не даст по сжатию, но зато, если эти символы окружены неповторяющимися символами,
                // в итоге строка будет только сильнее увеличена ("this mood" -> "6this m2o1d" или "9this mood", 11 или 10).
                // Минус этого алгоритма я пока нашла только в том, что если в результате добавления двух символов к
                // неповторяющейся строке ее длина станет больше MAX_USUAL_COUNT, то потребуется еще один байт для записи
                // количества неповторяющихся символов. И если, например, тескт будет таким:
                // ооппооппооппоопп...
                // то его сжатие по данному алгоритму займет больше места. Но, как мне кажется, первый случай более вероятен.

                    if (repeatCount > 2) {
                        // Записываем все накопившиеся, а если не накопилось, то list все равно пустой
                        if (usualCount > 0)
                            list.add(0, (usualCount + ZERO_FOR_USUAL).toByte())
                        res.addAll(list + (repeatCount + ZERO_FOR_REPEAT).toByte() + file[i])
                        // Обнуляем счетчик неповторяющихся символов
                        list.clear()
                        usualCount = 0
                    } else {
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
                        if (file[i] <= ZERO_FOR_REPEAT) {
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
                } catch (e: IndexOutOfBoundsException) {
                    error("Некорректное содержимое rle-файла как возможная причина ошибки")
                }
                if (i != lastIndex + 1) {
                    error("Некорректное содержимое rle-файла как возможная причина ошибки")
                }
                output.writeBytes(res.toByteArray())
            }
        } catch (e: FileNotFoundException) {
            error("Файл не найден")
        }
    }

}