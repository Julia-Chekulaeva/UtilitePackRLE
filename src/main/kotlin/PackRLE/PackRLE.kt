package main.kotlin.PackRLE

val regex = Regex("""pack-rle (-z|-u) (-out [а-яА-Я\w/]+\.txt )?[а-яА-Я\w/]+\.txt""")

class PackRLE(str: String) {

    init {
        if (!str.matches(regex))
            throw IllegalArgumentException("Утилита введена неправильно")
    }

    private val elems: Array<String> = str.split(" ")

    val inputName: String = elems.last()

    val outputName: String = if (elems.size == 5) elems[3] else elems[2].removeSuffix("txt") + "rle"

    val toRLE = elems[1] == "-z"

    fun utilite(str: String): Unit {
        val rle = PackRLE(str.substring(str.indexOf("pack-rle ")))
        val file = File(rle.inputName).readText()
        var i = 0
        var count: Int
        val lastIndex = file.lastIndex
        File(rle.outputName).bufferedWriter().use {
            if (rle.toRLE) {
                while (i < lastIndex) {
                    count = 1
                    while (i < lastIndex && file[i + 1] == file[i]) {
                        count++
                        i++
                    }
                    val needRLE = count > 4 || file[i] == '/' && count > 1
                    val c = if (file[i] == '/' && count == 1) "//" else file[i].toString()
                    if (needRLE) it.write("/$count/$c")
                    else for (j in 0 until count) it.write(c)
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
                                if (file[i] == '/' && i < lastIndex) i++ else throw IllegalArgumentException("Incorrect file content")
                                val needToWrite = StringBuilder()
                                for (j in 0 until count) needToWrite.append(file[i])
                                it.write(needToWrite.toString())
                            }
                            else -> throw IllegalArgumentException("Incorrect file content")
                        }
                    } else it.write(file[i].toString())
                    i++
                }
                if (i == lastIndex) it.write(file[i].toString())
            }
        }
    }

}