package packRLE

import kotlin.system.exitProcess

val errorMessages: List<String> = listOf(
        "Неправильно введена командная строка",
        "Ошибка при разборе командной строки. Определите тип конвертации: в rle или из rle",
        "Неправильное расширение файла(ов) или тип конвертации",
        "Файл не найден"
    )

fun error(code: Int) {
    System.err.println(errorMessages[code - 1])
    exitProcess(code)
}

fun main(args: Array<String>) {
    CmdClass.myParser(args)
}