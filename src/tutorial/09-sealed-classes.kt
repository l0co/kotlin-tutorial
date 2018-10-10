package tutorial

/**
 * Sealed Classes: https://kotlinlang.org/docs/reference/sealed-classes.html
 *
 * @author Lukasz Frankowski
 */

// NOTE: sealed class cannot be declared in {} block

private sealed class NamedThing constructor(val name: String) { // sealed class has default constructor private

    // sealed class is abstract by default
    abstract fun sayHello();

    class Mug(name: String): NamedThing(name) { // sealed class can be extended only by inner class

        override fun sayHello() {
            println("hello, mug")
        }

    }

}
