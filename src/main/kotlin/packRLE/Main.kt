package packRLE

import java.io.File
import kotlin.system.exitProcess

fun error(s: String) {
    System.err.println(s)
    exitProcess(-1)
}

fun main(args: Array<String>) {
    println(File("input\\Vikipedia.txt").readText() == File("input\\FromVikipedia.txt").readText())
    CmdClass().myParser(args)
}