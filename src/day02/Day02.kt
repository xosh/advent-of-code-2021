package day02

import readInput

fun main() {


    fun part1(input: List<String>): Int {

        var horizontalPos = 0
        var depth = 0

        input.forEach { commandLine ->
            val command = commandLine.split(" ")

            when (command[0]) {
                "forward" -> horizontalPos+=command[1].toInt()
                "down" -> depth+=command[1].toInt()
                "up" -> depth-=command[1].toInt()
            }
        }

        return horizontalPos*depth
    }

    fun part2(input: List<String>): Int {

        var horizontalPos = 0
        var depth = 0
        var aim = 0

        input.forEach { commandLine ->
            val command = commandLine.split(" ")

            when (command[0]) {
                "forward" -> {
                    horizontalPos+=command[1].toInt()
                    depth+=aim*command[1].toInt()
                }
                "down" -> aim+=command[1].toInt()
                "up" -> aim-=command[1].toInt()
            }
        }

        return horizontalPos*depth
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day02/Day02_test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("day02/Day02")
    println(part1(input))
    println(part2(input))
}
