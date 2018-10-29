package tutorial

/**
 * Reflection: https://kotlinlang.org/docs/reference/annotations.html
 *
 * @author Lukasz Frankowski
 */

// introspecting of local functions doesn't work yet in kotlin 1.2, so we need to define the function here
private fun get2(): Int = 2

fun main(args: Array<String>) {

    println("reflecting class")
    run {
        class Person
        println(Person::class.simpleName) // 1$Person: statically
        println(Person()::class.simpleName) // 1$Person: from object reference
        println(::Person.name) // <init>: constructor reference
    }

    println("\nreflecting functions and function reference")
    run {
        println(::get2.returnType) // kotlin.Int

        // with :: you can pass function reference as lambda somewhere
        fun printInt(f: () -> Int) {
            println(f())
        }
        printInt(::get2) // 2
    }

    println("\nreflecting properties")
    run {
        class Person(var name: String)
        val p = Person("Tom")

        println(Person::name.returnType) // kotlin.String: statically
        println(p::name.returnType) // kotlin.String: by instance
        println(Person::name.get(p)) // Tom: calling getter by reflection

        // passing getter reference to other function
        fun printName(f: () -> String) {
            println(f())
        }
        printName(p::name.getter) // Tom
    }

    println("\njava class vs kotlin class")
    run {
        class Person

        println(Person::class::class) // class kotlin.reflect.jvm.internal.KClassImpl
        println(Person::class.java::class) // class java.lang.Class: getting java class from kotlin class
        println(Person::class.java.kotlin::class) // class kotlin.reflect.jvm.internal.KClassImpl: getting kotlin class from java class
    }

    println("\nbound function and property reference")
    run {
        // method and property references retrieved from object instance are bound to the instance
        class Person(val name: String) {
            fun showThis() {
                println(this)
            }

            override fun toString(): String {
                return "Person($name)"
            }
        }
        val p = Person("Tom")

        val f = p::showThis
        f() // Person(Tom): has object reference remembered

        val g = p::name.getter
        println(g()) // Tom: has object reference remembered
    }
    
}