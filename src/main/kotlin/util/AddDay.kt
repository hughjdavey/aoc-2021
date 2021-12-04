package util

import java.io.File

object AddDay {

    @JvmStatic
    fun main(args: Array<String>) {
        if (args.isEmpty()) {
            System.err.println("Usage: gradle add-day <DAY-NUMBER>")
            return
        }

        val dayNumber = try {
            args[0].toInt()
        }
        catch (e: NumberFormatException) {
            System.err.println("Day argument must be an integer")
            return
        }

        val day = File("src/main/kotlin/days/Day$dayNumber.kt")
        val input = File("src/main/resources/input_day_$dayNumber.txt")
        val testDay = File("src/test/kotlin/days/Day$dayNumber.kt")
        val testInput = File("src/test/resources/input_day_$dayNumber.txt")

        input.createNewFile()
        testInput.createNewFile()

        if (day.createNewFile()) {
            day.appendText("""
                package days

                class Day$dayNumber : Day($dayNumber) {

                    override fun partOne(): Any {
                        return 0
                    }

                    override fun partTwo(): Any {
                        return 0
                    }
                }
                
            """.trimIndent())
        }

        if (testDay.createNewFile()) {
            testDay.appendText("""
                package days

                import org.hamcrest.MatcherAssert.assertThat
                import org.hamcrest.core.Is.`is`
                import org.junit.jupiter.api.Test

                class Day${dayNumber}Test {

                    private val day = Day${dayNumber}()

                    @Test
                    fun testPartOne() {
                        assertThat(day.partOne(), `is`(0))
                    }

                    @Test
                    fun testPartTwo() {
                        assertThat(day.partTwo(), `is`(0))
                    }
                }
                
            """.trimIndent())
        }
    }
}
