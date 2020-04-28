package packRLE

import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Tag
import java.io.File

class UtiliteTest {

    private fun utilite(s: String) {
        val args = s.split(" ").toTypedArray()
        CmdClass.myParser(args)
    }

    @Test
    @Tag("Example")
    fun utilite() {

        utilite("-z src\\test\\resources\\EmptyFile.txt")
        utilite("-u -out src\\test\\resources\\resOfEmptyFile.txt src\\test\\resources\\EmptyFile.rle")

        utilite("-z -out src\\test\\resources\\resOfTask2.rle src\\test\\resources\\task2.txt")
        utilite("-z src\\test\\resources\\task2.txt")
        utilite("-u -out src\\test\\resources\\resOfTask2FromRLE.txt src\\test\\resources\\resOfTask2.rle")

        utilite("-z -out src\\test\\resources\\resOfSomeText.rle src\\test\\resources\\SomeText.txt")
        utilite("-z src\\test\\resources\\SomeText.txt")
        utilite("-u -out src\\test\\resources\\SomeTextFromRLE.txt src\\test\\resources\\SomeText.rle")

        utilite("-z src\\test\\resources\\Vikipedia.txt")
        utilite("-u -out src\\test\\resources\\FromVikipedia.txt src\\test\\resources\\Vikipedia.rle")

        utilite("-z src\\test\\resources\\Repeating.txt")
        utilite("-u -out src\\test\\resources\\FromRepeating.txt src\\test\\resources\\Repeating.rle")

        assertEquals(
            File("src\\test\\resources\\EmptyFile.txt").readText(), File("src\\test\\resources\\resOfEmptyFile.txt").readText()
        )
        assertEquals(
                File("src\\test\\resources\\task2.rle").readText(), File("src\\test\\resources\\resOfTask2.rle").readText()
        )
        assertEquals(
                File("src\\test\\resources\\task2.txt").readText(), File("src\\test\\resources\\resOfTask2FromRLE.txt").readText()
        )
        assertEquals(
                File("src\\test\\resources\\SomeText.rle").readText(), File("src\\test\\resources\\resOfSomeText.rle").readText()
        )
        assertEquals(
                File("src\\test\\resources\\SomeText.txt").readText(), File("src\\test\\resources\\SomeTextFromRLE.txt").readText()
        )
        assertEquals(
            File("src\\test\\resources\\Vikipedia.txt").readText(), File("src\\test\\resources\\FromVikipedia.txt").readText()
        )
        assertEquals(
            File("src\\test\\resources\\Repeating.txt").readText(), File("src\\test\\resources\\FromRepeating.txt").readText()
        )

        File("src\\test\\resources\\resOfEmptyFile.txt").delete()
        File("src\\test\\resources\\EmptyFile.rle").delete()
        File("src\\test\\resources\\resOfTask2.rle").delete()
        File("src\\test\\resources\\task2.rle").delete()
        File("src\\test\\resources\\resOfTask2FromRLE.txt").delete()
        File("src\\test\\resources\\resOfSomeText.rle").delete()
        File("src\\test\\resources\\SomeText.rle").delete()
        File("src\\test\\resources\\SomeTextFromRLE.txt").delete()
        File("src\\test\\resources\\Vikipedia.rle").delete()
        File("src\\test\\resources\\FromVikipedia.txt").delete()
        File("src\\test\\resources\\Repeating.rle").delete()
        File("src\\test\\resources\\FromRepeating.txt").delete()
    }

}

