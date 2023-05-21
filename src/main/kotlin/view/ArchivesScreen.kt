package view

import model.Archive
import java.util.InputMismatchException
import java.util.Scanner

class ArchivesScreen : Screen() {

    private val archives = mutableListOf<Archive>()

    override fun start() {
        while (true) {
            printMenu()
            try {
                when (scanner.nextInt()) {
                    1 -> chooseArchive()
                    2 -> createArchive()
                    0 -> {
                        scanner.close()
                        return
                    }

                    else -> printUnknownCommandMessage()
                }
            } catch (exception: InputMismatchException) {
                printWrongNumberMessage(scanner)
                continue
            }
        }
    }

    private fun printMenu() {
        println("1. Выбрать архив.")
        println("2. Создать архив.")
        println("0. Выход.")
    }

    private fun chooseArchive() {
        if (archives.isEmpty()) {
            println("Вы не добавили ни одного архива.")
            return
        }
        while (true) {
            printArchivesMenu()
            try {
                when (val commandCode = scanner.nextInt()) {
                    in 1..archives.size -> {
                        val archive = archives[commandCode - 1]
                        OpenArchiveScreen(scanner, archive).start()
                    }

                    0 -> return
                    else -> printUnknownCommandMessage()
                }
            } catch (exception: InputMismatchException) {
                printWrongNumberMessage(scanner)
                continue
            }
        }
    }

    private fun printArchivesMenu() {
        println("Введите номер архива, который хотите выбрать:")
        archives.forEachIndexed { index, archive ->
            println("${index + 1}. ${archive.name}")
        }
        println("0. Вернуться к предыдущему меню.")
    }

    private fun createArchive() {
        scanner.nextLine()
        println("Введите название архива:")
        val archiveName = scanner.nextLine()
        archives.add(Archive(archiveName, mutableListOf()))
        println("Создан архив с названием \"$archiveName\".")
    }

    companion object {
        private val scanner = Scanner(System.`in`)
    }
}