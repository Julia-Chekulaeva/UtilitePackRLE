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

        utilite("-z src${File.separator}test${File.separator}resources${File.separator}EmptyFile.txt")
        utilite("-u -out src${File.separator}test${File.separator}resources${File.separator}resOfEmptyFile.txt src${File.separator}test${File.separator}resources${File.separator}EmptyFile.rle")

        utilite("-z -out src${File.separator}test${File.separator}resources${File.separator}resOfTask2.rle src${File.separator}test${File.separator}resources${File.separator}task2.txt")
        utilite("-z src${File.separator}test${File.separator}resources${File.separator}task2.txt")
        utilite("-u -out src${File.separator}test${File.separator}resources${File.separator}resOfTask2FromRLE.txt src${File.separator}test${File.separator}resources${File.separator}resOfTask2.rle")

        utilite("-z -out src${File.separator}test${File.separator}resources${File.separator}resOfSomeText.rle src${File.separator}test${File.separator}resources${File.separator}SomeText.txt")
        utilite("-z src${File.separator}test${File.separator}resources${File.separator}SomeText.txt")
        utilite("-u -out src${File.separator}test${File.separator}resources${File.separator}SomeTextFromRLE.txt src${File.separator}test${File.separator}resources${File.separator}SomeText.rle")

        utilite("-z src${File.separator}test${File.separator}resources${File.separator}Vikipedia.txt")
        utilite("-u -out src${File.separator}test${File.separator}resources${File.separator}FromVikipedia.txt src${File.separator}test${File.separator}resources${File.separator}Vikipedia.rle")

        utilite("-z src${File.separator}test${File.separator}resources${File.separator}Repeating.txt")
        utilite("-u -out src${File.separator}test${File.separator}resources${File.separator}FromRepeating.txt src${File.separator}test${File.separator}resources${File.separator}Repeating.rle")

        assertEquals(
            File("src${File.separator}test${File.separator}resources${File.separator}EmptyFile.txt").readText(), File("src${File.separator}test${File.separator}resources${File.separator}resOfEmptyFile.txt").readText()
        )
        assertEquals(
                File("src${File.separator}test${File.separator}resources${File.separator}task2.rle").readText(), File("src${File.separator}test${File.separator}resources${File.separator}resOfTask2.rle").readText()
        )
        assertEquals(
                File("src${File.separator}test${File.separator}resources${File.separator}task2.txt").readText(), File("src${File.separator}test${File.separator}resources${File.separator}resOfTask2FromRLE.txt").readText()
        )
        assertEquals(
                File("src${File.separator}test${File.separator}resources${File.separator}SomeText.rle").readText(), File("src${File.separator}test${File.separator}resources${File.separator}resOfSomeText.rle").readText()
        )
        assertEquals(
                File("src${File.separator}test${File.separator}resources${File.separator}SomeText.txt").readText(), File("src${File.separator}test${File.separator}resources${File.separator}SomeTextFromRLE.txt").readText()
        )
        assertEquals(
            File("src${File.separator}test${File.separator}resources${File.separator}Vikipedia.txt").readText(), File("src${File.separator}test${File.separator}resources${File.separator}FromVikipedia.txt").readText()
        )
        assertEquals(
            File("src${File.separator}test${File.separator}resources${File.separator}Repeating.txt").readText(), File("src${File.separator}test${File.separator}resources${File.separator}FromRepeating.txt").readText()
        )

        File("src${File.separator}test${File.separator}resources${File.separator}resOfEmptyFile.txt").delete()
        File("src${File.separator}test${File.separator}resources${File.separator}EmptyFile.rle").delete()
        File("src${File.separator}test${File.separator}resources${File.separator}resOfTask2.rle").delete()
        File("src${File.separator}test${File.separator}resources${File.separator}task2.rle").delete()
        File("src${File.separator}test${File.separator}resources${File.separator}resOfTask2FromRLE.txt").delete()
        File("src${File.separator}test${File.separator}resources${File.separator}resOfSomeText.rle").delete()
        File("src${File.separator}test${File.separator}resources${File.separator}SomeText.rle").delete()
        File("src${File.separator}test${File.separator}resources${File.separator}SomeTextFromRLE.txt").delete()
        File("src${File.separator}test${File.separator}resources${File.separator}Vikipedia.rle").delete()
        File("src${File.separator}test${File.separator}resources${File.separator}FromVikipedia.txt").delete()
        File("src${File.separator}test${File.separator}resources${File.separator}Repeating.rle").delete()
        File("src${File.separator}test${File.separator}resources${File.separator}FromRepeating.txt").delete()
    }

}

