package packRLE

import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Tag
import java.io.File

class UtiliteTest {

    private fun utilite(s: String) {
        val args = s.split(" ").toTypedArray()
        CmdClass().myParser(args)
    }

    @Test
    @Tag("Example")
    fun utilite() {

        utilite("pack-rle -z input\\EmptyFile.txt")
        utilite("pack-rle -u -out input\\resOfEmptyFile.txt input\\EmptyFile.rle")

        utilite("pack-rle -z -out input\\resOfTask2.rle input\\task2.txt")
        utilite("pack-rle -z input\\task2.txt")
        utilite("pack-rle -u -out input\\resOfTask2FromRLE.txt input\\resOfTask2.rle")

        utilite("pack-rle -z -out input\\resOfSomeText.rle input\\SomeText.txt")
        utilite("pack-rle -z input\\SomeText.txt")
        utilite("pack-rle -u -out input\\SomeTextFromRLE.txt input\\SomeText.rle")

        utilite("pack-rle -z input\\Vikipedia.txt")
        utilite("pack-rle -u -out input\\FromVikipedia.txt input\\Vikipedia.rle")

        assertEquals(
            File("input\\EmptyFile.txt").readText(), File("input\\resOfEmptyFile.txt").readText()
        )
        assertEquals(
                File("input\\task2.rle").readText(), File("input\\resOfTask2.rle").readText()
        )
        assertEquals(
                File("input\\task2.txt").readText(), File("input\\resOfTask2FromRLE.txt").readText()
        )
        assertEquals(
                File("input\\SomeText.rle").readText(), File("input\\resOfSomeText.rle").readText()
        )
        assertEquals(
                File("input\\SomeText.txt").readText(), File("input\\SomeTextFromRLE.txt").readText()
        )
        assertEquals(
            File("input\\Vikipedia.txt").readText(), File("input\\FromVikipedia.txt").readText()
        )

        File("input\\resOfEmptyFile.txt").delete()
        File("input\\EmptyFile.rle").delete()
        File("input\\resOfTask2.rle").delete()
        File("input\\task2.rle").delete()
        File("input\\resOfTask2FromRLE.txt").delete()
        File("input\\resOfSomeText.rle").delete()
        File("input\\SomeText.rle").delete()
        File("input\\SomeTextFromRLE.txt").delete()
        File("input\\Vikipedia.rle").delete()
        File("input\\FromVikipedia.txt").delete()
    }

}

