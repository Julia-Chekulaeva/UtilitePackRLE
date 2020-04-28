package packRLE

import org.apache.commons.cli.*
import kotlin.system.exitProcess

class CmdClass {

    companion object {

        @JvmStatic
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
                if (cmd.args.size != 1)
                    error(1)
                if (cmd.hasOption("z") == cmd.hasOption("u")) {
                    error(2)
                }
                val outputName = if (cmd.hasOption("out"))
                    cmd.getOptionValue("out")
                else
                    null
                PackRLE(cmd.hasOption("z"), outputName, cmd.args[0]).utilite()
            } else {
                // Скорее всего, перенос строки здесь не нужен
                System.err.println("Ошибка при разборе командной строки")
                exitProcess(-2)
            }
        }

    }

}