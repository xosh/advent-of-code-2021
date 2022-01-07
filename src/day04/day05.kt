package day04

import readInput
import kotlin.collections.ArrayList

fun main() {

    class BingoField(val number: Int) {

        var marked: Boolean = false
            get() = field
            set(value) {
                field = value
            }

    }

    class BingoBoard(val fields: ArrayList<BingoField>) {

        fun markNumber(number: Int){
            fields.forEach { field ->
                if (field.number == number){
                    field.marked = true
                }
            }
        }

        fun bingoInColumn(columnNumber: Int): Boolean{
            return fields[columnNumber].marked && fields[5+columnNumber].marked&& fields[10+columnNumber].marked && fields[15+columnNumber].marked && fields[20+columnNumber].marked
        }

        fun bingoInRow(rowNumber: Int): Boolean{
            return fields[rowNumber*5].marked && fields[rowNumber*5+1].marked && fields[rowNumber*5+2].marked && fields[rowNumber*5+3].marked && fields[rowNumber*5+4].marked
        }

        fun bingo(): Boolean{
            for (i in 0..4){
                if (bingoInColumn(i) || bingoInRow(i)){
                    return true
                }
            }
            return false
        }

        fun score(): Int {
            return fields.map { field ->
                if (!field.marked){
                    field.number
                } else 0
            }.sum()
        }

        fun print(){
            fields.forEach { field ->
                print(field.number)
                print(";")
            }
            println()
        }

    }

    fun part1(input: List<String>): Int {

        var boards = ArrayList<BingoBoard>()

        //Extract numbers from input
        val randomNumbers = input[0].split(",").map { number -> number.toInt() }

        var tmp = ArrayList<BingoField>()

        //Extract boards from input
        for (i in 2 until input.size) {
            tmp.addAll(input[i].split(" +".toRegex()).filter { numberString -> numberString.isNotEmpty() }.map { numberString -> BingoField(numberString.toInt())})
            if (tmp.size == 25){
                boards.add(BingoBoard(tmp))
                tmp = ArrayList()
            }
        }

        //Play
        randomNumbers.forEach { number ->
            boards.forEach { board ->
                board.markNumber(number)
                if (board.bingo()){
                    println("number: $number")
                    println("score: ${board.score()}")
                    println("BINGO!")

                    return number * board.score()
                }
            }
        }

        return 0
    }

    fun part2(input: List<String>): Int {

        var boards = ArrayList<BingoBoard>()

        //Extract numbers from input
        val randomNumbers = input[0].split(",").map { number -> number.toInt() }

        var tmp = ArrayList<BingoField>()

        //Extract boards from input
        for (i in 2 until input.size) {
            tmp.addAll(input[i].split(" +".toRegex()).filter { numberString -> numberString.isNotEmpty() }.map { numberString -> BingoField(numberString.toInt())})
            if (tmp.size == 25){
                boards.add(BingoBoard(tmp))
                tmp = ArrayList()
            }
        }

        var winningScores = ArrayList<Int>()

        //Play
        randomNumbers.forEach { number ->
            boards.forEach { board ->
                if (!board.bingo()){
                    board.markNumber(number)
                    if (board.bingo()){
                        println("number: $number")
                        println("score: ${board.score()}")
                        println("BINGO!")

                        winningScores.add(number*board.score())
                    }
                }
            }
        }

        return winningScores.last()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day04/Day04_test")
    check(part1(testInput) == 4512)
    check(part2(testInput) == 1924)

    val input = readInput("day04/Day04")
    println(part1(input))
    println(part2(input))
}
