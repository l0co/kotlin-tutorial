package tutorial

/**
 * Data Classes: https://kotlinlang.org/docs/reference/data-classes.html
 *
 * @author Lukasz Frankowski
 */
@Suppress("ReplaceCallWithBinaryOperator")
fun main(args: Array<String>) {

    println("data class basics")
    run {
        // data class = automatically generated, non-open (final) class with all properties specified in constructor

        data class User(val name: String, val age: Int)

        val u1 = User("Tom", 10)
        val u2 = User("Tom", 10)

        println(u1.equals(u2)) // true: data class has auto equals() 
        println("${u1.hashCode()} / ${u2.hashCode()}") // 2612504 / 2612504: auto hashCode()
        println(u1.toString())  // User(name=Tom, age=10): auto toString()
        println(u1.copy()) // User(name=Tom, age=10): auto copy()
        println(u1.copy(age = 12)) // User(name=Tom, age=12): copy() with replacing some property value

        // if equals(), hashCode() and toString() are provided by the user, these methods will be used instead of auto

        data class User1(val name: String, val age: Int) {
            override fun equals(other: Any?): Boolean {
                return false
            }

            override fun hashCode(): Int {
                return 1
            }

            override fun toString(): String {
                return name
            }
        }

        val u3 = User1("Tom", 10)
        val u4 = User1("Tom", 10)
        println(u3.equals(u4)) // false
        println("${u3.hashCode()} / ${u4.hashCode()}") // 1 / 1
        println(u3.toString())  // Tom
    }

    println("\nparameterless constructor")
    run {
        // data class has no default parameterless constructor
        data class User(val name: String, val age: Int)
        // User() // Error:(54, 14) Kotlin: No value passed for parameter 'name' / 'age'

        // parameterless constructor is created when all constructor arguments have default values
        data class User1(val name: String = "", val age: Int = 0)
        User1()
    }

    println("\ncustom properties are not included in auto generated methods")
    run {
        data class User(val name: String) {
            var age: Int = 0
        }
        val u1 = User("Tom")
        val u2 = User("Tom")
        u1.age = 10
        u2.age = 12
        println(u1.equals(u2)) // true
    }

    println("\nstandard data classes")
    run {
        println(Pair(1, "text")) // (1, text)
        println(Triple(1, "text", true)) // (1, text, true)
    }

}