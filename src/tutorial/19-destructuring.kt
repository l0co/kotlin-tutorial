package tutorial

/**
 * Destructuring: https://kotlinlang.org/docs/reference/multi-declarations.html
 *
 * @author Lukasz Frankowski
 */

private class Person(val name: String, val age: Int) {

    operator fun component1(): String = name // the result of this method will be assigned to first destructuring variable
    operator fun component2(): Int = age // the result of this method will be assigned to first destructuring variable

}

fun main(args: Array<String>) {

    println("object destructuring")
    run {
        // destructuring assigns results of object's componentX operator method to subsequent variable
        val (v1, v2) = Person("Tom", 10)
        println("$v1, $v2") // Tom, 10

        val (_, age) = Person("Tom", 10) // use _ (underscore) for unused variables
        println(age) // 10

        // usage in for loops
        for ((v3, v4) in arrayOf(Person("Tom", 10), Person("Joe", 12)))
            println("$v3, $v4") // Tom, 10 ... Joe, 12
    }

    println("\ndata classes automatically implement componentX operators")
    run {
        data class Result(val x: Int, val y: String)
        fun getResult(): Result = Result(1, "text")
        val (v1, v2) = getResult()
        println("$v1, $v2") // 1, text

        // built-in classes Pair and Tripple are useful for such cases like returning two/three values from the funtion
        fun getResult2(): Pair<Int, String> = Pair(1, "text")
        val (v3, v4) = getResult2()
        println("$v3, $v4") // 1, text
    }

    println("\niterating over map with destructuring")
    run {
        val map = mapOf("a" to 1, "b" to 2)
        for ((k, v) in map)
            println("$k => $v") // a => 1 ... b => 2
    }

    println("\nusing destructuring in lambda")
    run {
        // standard lambda call
        var mul: (pair: Pair<Int, Int>) -> Int = { pair ->  pair.first * pair.second }
        println(mul(Pair(2, 3))) // 6

        // destructuring Pair into lamda arguments
        mul = { (x, y) -> x * y}
        println(mul(Pair(2, 3))) // 6
    }

}