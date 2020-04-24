package packRLE

import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.assertThrows
import java.io.File
import java.lang.IllegalStateException
import java.lang.IndexOutOfBoundsException

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

        utilite("pack-rle -z input\\Repeating.txt")
        utilite("pack-rle -u -out input\\FromRepeating.txt input\\Repeating.rle")

        assertThrows<IllegalStateException> { utilite("pack-rle -z -u input\\Repeating.txt") }
        assertThrows<IllegalStateException> { utilite("pack-rle -z -out input\\Repeating.rle") }
        assertThrows<IllegalStateException> { utilite("pack-r -z input\\Repeating.txt") }
        assertThrows<IllegalStateException> { utilite("pack-rle -z") }
        assertThrows<IllegalStateException> { utilite("pack-rle -z input\\Repeating.rle") }
        assertThrows<IllegalStateException> { utilite("pack-rle -z -u -out input\\Repeating.txt input\\Repeating.txt") }
        assertThrows<IndexOutOfBoundsException> { utilite("pack-rle -u input\\FromVikipediaWrong.rle") }
        assertThrows<IndexOutOfBoundsException> { utilite("pack-rle -u input\\FromVikipediaWrong2.rle") }
        assertThrows<java.io.FileNotFoundException> { utilite("pack-rle -z input\\NonExistingFile.txt") }

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
        assertEquals(
            File("input\\Repeating.txt").readText(), File("input\\FromRepeating.txt").readText()
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
        File("input\\Repeating.rle").delete()
        File("input\\FromRepeating.txt").delete()
    }

}

