package packRLE

import java.lang.IllegalStateException
import java.lang.IndexOutOfBoundsException
import kotlin.system.exitProcess

fun error(s: String) {
    System.err.println(s)
    exitProcess(-1)
}

fun main(args: Array<String>) {
    try {
        CmdClass().myParser(args)
    } catch (e: IllegalStateException) {
        // Расширение у файла не совпадает с требуемым
        if (e.message == "расширение")
            error("Неправильное расширение файла(ов) или тип конвертации")
        if (e.message == "args")
            error("Неправильно введена командная строка")
        // Указаны 2 типа или не указано ни одного
        if (e.message == "z or u")
            error("Ошибка при разборе командной строки. Определите тип конвертации: в rle или из rle")
    } catch (e: IndexOutOfBoundsException) {
        // Это, скорее всего, случай, когда индекс выходит за границы файла или не доходит до них, т.е. содержимое rle-файла некорректно
        error("Некорректное содержимое rle-файла как возможная причина ошибки")
    } catch (e: java.io.FileNotFoundException) {
        error("Файл не найден")
    }
}