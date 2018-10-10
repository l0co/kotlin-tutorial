package tutorial

/**
 * Equality: https://kotlinlang.org/docs/reference/equality.html
 *
 * @author Lukasz Frankowski
 */
@Suppress("ReplaceCallWithBinaryOperator")
fun main(args: Array<String>) {

    println("equality check")

    data class Person(val name: String) // data class has built-in equals() on all properties introduced in constructor

    val p1: Person? = Person("Tom")
    val p2: Person? = Person("Tom")

    println(p1?.equals(p2)) // true
    println(p1 == p2) // true: kotlin uses == as the equivalent of equals()

    run {
        val p3: Person? = null
        val p4: Person? = null

        println(p3 == p4) // true: note, than null-s are equals()
    }

    println(p1 === p2) // false: === stands for referential equality and check if both sides represents the same object instance

}