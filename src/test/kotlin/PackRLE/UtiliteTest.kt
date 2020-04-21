package PackRLE

import main.kotlin.PackRLE.PackRLE
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Tag
import java.io.File

class UtiliteTest {

    // Раз уж класс паф, нужно потестить, как отсутствие входного файла обыгрывается
    // Ок, все равно нужно постестить

    /*private fun utilite(s: String) {
        val obj = PackRLE(s.split(" "))
        obj.utilite()
    }

    @Test
    @Tag("Example")
    fun utilite() {
        utilite("pack-rle -z input/EmptyFile.txt")
        utilite("pack-rle -u -out input/resOfEmptyFile.txt input/EmptyFile.rle")

        utilite("pack-rle -z -out input\\resOfTask2.rle input\\task2.txt")
        utilite("pack-rle -z input\\task2.txt")

        utilite("pack-rle -u -out input\\resOfTask2RLE.txt input\\task2RLE.rle")
        utilite("pack-rle -u input\\task2RLE.rle")

        utilite("pack-rle -z -out input\\resOfSomeText.rle input\\SomeText.txt")
        utilite("pack-rle -z input\\SomeText.txt")

        utilite("pack-rle -u -out input\\SomeTextFromRLE.txt input\\SomeText.rle")
        utilite("pack-rle -u input\\resOfSomeText.rle")

        assertEquals(
            File("input\\EmptyFile.txt").readText(), File("input\\resOfEmptyFile.txt").readText()
        )
        assertEquals(
                File("input\\task2.rle").readText(), File("input\\resOfTask2.rle").readText()
        )
        assertEquals(
                File("input\\task2RLE.txt").readText(), File("input\\resOfTask2RLE.txt").readText()
        )
        assertEquals(
                File("input\\task2RLE.rle").readText(), File("input\\resOfTask2.rle").readText()
        )
        assertEquals(
                File("input\\SomeText.rle").readText(), File("input\\resOfSomeText.rle").readText()
        )
        assertEquals(
                File("input\\SomeText.txt").readText(), File("input\\SomeTextFromRLE.txt").readText()
        )
        File("input/resOfEmptyFile.txt").delete()
        File("input/EmptyFile.rle").delete()
        File("input/resOfTask2.rle").delete()
        File("input/task2.rle").delete()
        File("input/resOfTask2RLE.txt").delete()
        File("input/resOfSomeText.rle").delete()
        File("input/SomeText.rle").delete()
        File("input/resOfSomeText.txt").delete()
        File("input/SomeTextRLE.txt").delete()
        File("input/SomeTextFromRLE.txt").delete()
    }*/

}

