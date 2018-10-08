package tutorial

/**
 * Objects: https://kotlinlang.org/docs/reference/object-declarations.html
 *
 * @author Lukasz Frankowski
 */

// object expression can be used to create singleton objects
object MySingleton {
    fun hello() {
        println("hello, singleton")
    }
}

// classes can have companion object which is the way to add static methods to the class
class CompanionClass {

    companion object {
        fun hello() {
            println("hello, default companion object")
        }
    }
}

// default companion object is called Companion, but it also can have custom name
class CompanionClass2 {

    companion object Other {
        fun hello() {
            println("hello, named companion object")
        }
    }
    
}


fun main(args: Array<String>) {

    println("object expression")
    run {

        var greetable: Greetable = object: Greetable { // object expression creates ad-hoc anonymous class and its instance
            override fun hello() {
                println("hello")
            }
        }
        greetable.hello() // hello

        greetable = object: NamedGreetable("Tom") {} // example of constructor usage
        greetable.hello() // hello, Tom

        val obj = object { // it's also possible to create just an object without supertypes
            var x: Int = 1

            fun hello() {
                greetable.hello()
            }
        }
        println(obj.x) // 1: locally, the type is known and its property/method can be referenced
        obj.hello() // hello, Tom: object has the access to the scope it was created

    }

    println("\nsingleton declaration")
    run {
        MySingleton.hello() // hello, singleton: example of singleton usage
    }

    println("\ncompanion object")
    run {
        CompanionClass.Companion.hello() // hello, default companion object
        CompanionClass2.Other.hello() // hello, named companion object
    }

}