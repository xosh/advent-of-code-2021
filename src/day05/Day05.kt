package day05

import readInput
import kotlin.math.abs
import kotlin.math.max

fun main() {


    fun lineHorizontalOrVertical(lineCoordinates: List<Pair<Int, Int>>): Boolean {
        return (lineCoordinates[0].first == lineCoordinates[1].first) or (lineCoordinates[0].second == lineCoordinates[1].second)
    }

    fun lineDiagonal(lineStartAndEndCoordinates: List<Pair<Int, Int>>): Boolean {
        return abs(lineStartAndEndCoordinates[0].first-lineStartAndEndCoordinates[1].first) == abs(lineStartAndEndCoordinates[0].second-lineStartAndEndCoordinates[1].second)
    }


    fun markLineInGrid(
        lineStartAndEndCoordinates: List<Pair<Int, Int>>,
        grid: Array<IntArray>
    ) {
        //calc all points of line
        val start = lineStartAndEndCoordinates[0]
        val end = lineStartAndEndCoordinates[1]

        var xs = if (start.first <= end.first) {
            start.first.rangeTo(end.first)
        } else {
            start.first.downTo(end.first)
        }.toList()
        var ys = if (start.second <= end.second) {
            start.second.rangeTo(end.second)
        } else {
            start.second.downTo(end.second)
        }.toList()

        //horizontal
        if (xs.size == 1) {
            xs = List(ys.size + 1) { xs.first() }
        }
        //vertical
        if (ys.size == 1) {
            ys = List(xs.size + 1) { ys.first() }
        }

        xs.zip(ys).forEach { (x, y) -> grid[x][y]++ }
    }


    fun part1(input: List<String>): Int {

        var maxX = 0
        var maxY = 0

        //calc max x and y
        for (line in input){
            line.split(" -> ").forEach { coordinate ->
                maxX = Math.max(coordinate.split(",")[0].toInt(),maxX)
                maxY = Math.max(coordinate.split(",")[1].toInt(),maxY)
            }
        }

        val grid = Array(maxX+1) { IntArray(maxY+1) }

        //add lines
        for (line in input){
            //extract values
            val lineStartAndEndCoordinates = line.split(" -> ")
                .map { coordinate ->
                    Pair(coordinate.split(",")[0].toInt(),coordinate.split(",")[1].toInt()) }
                .toList()

            if (lineHorizontalOrVertical(lineStartAndEndCoordinates)){
                //add line in grid
                markLineInGrid(lineStartAndEndCoordinates, grid)

            }
        }

        //check how many times over 2
        return grid.map { col ->
            col.filter { number -> number >= 2 }.count()
        }.sum()
    }


    fun generateGrid(input: List<String>): Array<IntArray> {
        var maxX = 0
        var maxY = 0

        //calc max x and y
        for (line in input) {
            line.split(" -> ").forEach { coordinate ->
                maxX = max(coordinate.split(",")[0].toInt(), maxX)
                maxY = max(coordinate.split(",")[1].toInt(), maxY)
            }
        }

        return Array(maxX + 1) { IntArray(maxY + 1) }
    }

    fun part2(input: List<String>): Int {

        val grid = generateGrid(input)

        //add lines
        for (line in input){
            //extract values
            val lineStartAndEndCoordinates = line.split(" -> ")
                .map { coordinate ->
                    Pair(coordinate.split(",")[0].toInt(),coordinate.split(",")[1].toInt()) }
                .toList()

            if (lineHorizontalOrVertical(lineStartAndEndCoordinates) or lineDiagonal(lineStartAndEndCoordinates)){
                //add line in grid
                markLineInGrid(lineStartAndEndCoordinates, grid)
            }
        }

        //check how many times over 2
        return grid.map { col ->
            col.filter { number -> number >= 2 }.count()
        }.sum()
    }
    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day05/Day05_test")
    check(part1(testInput) == 5)
    check(part2(testInput) == 12)

    val input = readInput("day05/Day05")
    println(part1(input))
    println(part2(input))
}
