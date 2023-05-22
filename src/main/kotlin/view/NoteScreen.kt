package view

import model.Note
import java.util.InputMismatchException
import java.util.Scanner

class NoteScreen(private val scanner: Scanner, private val note: Note) : Screen() {

    override fun start() {
        println("Заметка \"${note.title}\"")
        while (true) {
            printMenu()
            try {
                when (scanner.nextInt()) {
                    1 -> println(note.text)
                    0 -> return
                    else -> printWrongNumberMessage(scanner)
                }
            } catch (exception: InputMismatchException) {
                printWrongNumberMessage(scanner)
                continue
            }

        }
    }

    private fun printMenu() {
        println("1. Показать текст.")
        println("0. Вернуться к предыдущему меню.")
    }
}