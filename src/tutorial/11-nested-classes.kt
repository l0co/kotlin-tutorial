package tutorial

/**
 * Nested Classes: https://kotlinlang.org/docs/reference/nested-classes.html
 *
 * @author Lukasz Frankowski
 */

private class Outer {

    var greetingsCount: Int = 0;

    class Nested: Greetable { // default nested class doesn't have connection to outer class instance (java equivalent is nested static class)
        override fun hello() {
            println("hello")
            // greetingsCount++; // Error:(16, 13) Kotlin: Unresolved reference: x
        }
    }

    inner class Inner: Greetable { // inner class is connected with outer class instance
        override fun hello() {
            println("hello")
            greetingsCount++; // can use outer class property

            this@Outer.greetingsCount; // example of qualified outer class "this"
        }
    }

    fun hello(g: Greetable) {
        g.hello()
    }

    fun exampleHello() {
        hello(object: Greetable { // anonymous class can be created with "object" clause
            
            override fun hello() {
                println("hi")
            }

        })
    }
    
}

fun main(args: Array<String>) {

    println("nested class")
    run {
        Outer.Nested().hello() // it's only name qualifier
    }

    println("\ninner class")
    run {
        Outer().Inner().hello() // outer class instance is required to instantiate inner class
    }

    println("\nanonymous class")
    run {
        Outer().exampleHello()
    }

}