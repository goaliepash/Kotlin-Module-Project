package view

import model.Archive
import model.Note
import java.util.InputMismatchException
import java.util.Scanner

class OpenArchiveScreen(private val scanner: Scanner, private val archive: Archive) : Screen() {

    override fun start() {
        while (true) {
            printMenu()
            try {
                when (scanner.nextInt()) {
                    1 -> chooseNote()
                    2 -> createNote()
                    0 -> return
                    else -> printUnknownCommandMessage()
                }
            } catch (exception: InputMismatchException) {
                printWrongNumberMessage(scanner)
                continue
            }
        }
    }

    private fun printMenu() {
        println("1. Выбрать заметку.")
        println("2. Создать заметку.")
        println("0. Вернуться к предыдущему меню.")
    }

    private fun chooseNote() {
        if (archive.notes.isEmpty()) {
            println("Вы не добавили ни одной заметки.")
            return
        }
        while (true) {
            printNotesMenu()
            try {
                when (val commandCode = scanner.nextInt()) {
                    in 1..archive.notes.size -> {
                        val note = archive.notes[commandCode - 1]
                        NoteScreen(scanner, note).start()
                    }

                    0 -> {
                        return
                    }

                    else -> printWrongNumberMessage(scanner)
                }
            } catch (exception: InputMismatchException) {
                printWrongNumberMessage(scanner)
                continue
            }
        }
    }

    private fun createNote() {
        println("Введите заголовок заметки:")
        scanner.nextLine()
        val title = scanner.nextLine()
        println("Введите текст заметки:")
        val text = scanner.nextLine()
        archive.notes.add(Note(title, text))
        println("Заметка \"$title\" создана.")
    }

    private fun printNotesMenu() {
        println("Введите номер заметки, которую хотите выбрать:")
        archive.notes.forEachIndexed { index, note ->
            println("${index + 1}. ${note.title}")
        }
        println("0. Вернуться к предыдущему меню.")
    }
}