package packRLE

import org.apache.commons.cli.*
import kotlin.system.exitProcess

class CmdClass {
    fun myParser(myArgs: Array<String>) {
        val options = Options()
        options.addOption("z", false, "Converting from txt to rle")
        options.addOption("u", false, "Converting from rle to txt")
        options.addOption("out", true, "Output file")
        options.getOption("out").argName = "outputName"
        val parser = DefaultParser()
        val cmd: CommandLine?
        try {
            cmd = parser.parse(options, myArgs)
        } catch (pe: ParseException) {
            val formatter = HelpFormatter()
            formatter.printHelp("PackRLE", options)
            exitProcess(-1)
        }
        if (cmd != null) {
            if (cmd.hasOption("z") == cmd.hasOption("u")) {
                error("Ошибка при разборе командной строки. Определите тип конвертации")
            }
            val outputName = if (cmd.hasOption("out"))
                cmd.getOptionValues("out")[0]
            else
                null
            PackRLE(cmd.hasOption("z"), outputName, myArgs.last()).utilite()
        } else {
            error("Ошибка при разборе командной строки\n")
        }
    }
}