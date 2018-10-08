package tutorial

/**
 * Common types for all examples.
 *
 * @author Lukasz Frankowski
 */

interface Greetable {
    fun hello()
}

open class NamedGreetable(val name: String): Greetable {

    override fun hello() {
        println("hello, $name")
    }

}