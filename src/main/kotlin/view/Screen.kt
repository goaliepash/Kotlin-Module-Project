package view

import java.util.Scanner

abstract class Screen {

    abstract fun start()

    protected fun printWrongNumberMessage(scanner: Scanner) {
        scanner.nextLine()
        println("Необходимо ввести число, представленное в меню!")
    }

    protected fun printUnknownCommandMessage() {
        println("Неизвестная команда! Выберите число, представленное в меню.")
    }
}