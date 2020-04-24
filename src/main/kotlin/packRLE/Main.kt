package packRLE

import kotlin.system.exitProcess

fun error(s: String) {
    System.err.println(s)
    exitProcess(-1)
}

fun main(args: Array<String>) {
    CmdClass().myParser(args)
}