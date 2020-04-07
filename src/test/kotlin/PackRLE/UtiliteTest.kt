package PackRLE

import org.junit.Test
import kotlin.test.assertEquals
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import java.io.File

class UtiliteTest {

    @Test
    @Tag("Example")
    fun utilite() {
        utilite("pack-rle -z -out input/resOfTask2.txt input/task2.txt")
        utilite("pack-rle -z input/task2.txt")
        utilite("pack-rle -u -out input/resOfTask2RLE.txt input/task2RLE.txt")
        utilite("pack-rle -u input/task2RLE.txt")
        utilite("pack-rle -z -out input/resOfSomeText.txt input/SomeText.txt")
        utilite("pack-rle -u input/SomeTextRLE.txt")
        utilite("pack-rle -u -out input/SomeTextFromRLE.txt input/resOfSomeText.txt")
        assertEquals(
                File("input/task2RLE.rle").readText(), File("input/resOfTask2RLE.txt").readText()
        )
        assertEquals(
                File("input/task2.rle").readText(), File("input/resOfTask2.txt").readText()
        )
        assertEquals(
                File("input/task2.txt").readText(), File("input/resOfTask2RLE.txt").readText()
        )
        assertEquals(
                File("input/task2RLE.txt").readText(), File("input/resOfTask2.txt").readText()
        )
        assertEquals(
                File("input/SomeText.txt").readText(), File("input/SomeTextRLE.rle").readText()
        )
        assertEquals(
                File("input/SomeText.txt").readText(), File("input/SomeTextFromRLE.txt").readText()
        )
        File("input/resOfTask2.txt").delete()
        File("input/task2.rle").delete()
        File("input/resOfTask2RLE.txt").delete()
        File("input/task2RLE.rle").delete()
        File("input/resOfSomeText.txt").delete()
        File("input/SomeTextRLE.rle").delete()
        File("input/SomeTextFromRLE.txt").delete()
    }

}

