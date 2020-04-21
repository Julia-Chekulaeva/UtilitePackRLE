package main.kotlin.PackRLE

import org.apache.commons.cli.*
import kotlin.system.exitProcess


fun main(args: Array<String>) {
    val options = Options()
    options.addOption("z", false, "Converting from txt to rle")
    options.addOption("u", false, "Converting from rle to txt")
    options.addOption("out", true, "Output file")
    val parser = DefaultParser()
    var cmd: CommandLine? = null
    try {
        cmd = parser.parse(options, args)
    } catch (pe: ParseException) {
        val formatter = HelpFormatter()
        formatter.printHelp("PackRLE", options)
    }
    if (cmd != null) {
        if (cmd.hasOption("z") == cmd.hasOption("u")) {
            System.out.println("Неправильный ввод командной строки")
            exitProcess(-1)
        }
        val outputName = if (cmd.hasOption("out")) options.getOption("out").argName else null
        PackRLE(/*args, */cmd.hasOption("z"), outputName, args.last()).utilite()
    } else {
        System.out.println("Ошибка при разборе командной строки")
        exitProcess(-1)
    }
}