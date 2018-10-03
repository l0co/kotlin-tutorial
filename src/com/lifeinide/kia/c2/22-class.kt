package com.lifeinide.kia.c2

fun main(args: Array<String>) {

    println("value object")
    run {
        class Person(val name: String, private val surname: String)
        val p = Person("Tom", "Smith")
        println("new person: ${p.name}")
        // println("new person: ${p.surname}") // can't access - private
    }

    println("\ngetters and setters")
    run {
        class Person {
            var name: String = "" // this will get getter and setter
            var surname: String = "" // this will get getter and setter
            val married: Boolean = false // this will get getter only (val = readonly)

            val fullname: String
                get() {
                    return "$name $surname" // because of custom getter this property doesn't create a field
                }

        }
        val p = Person()
        p.name = "Tom" // setter access
        p.surname = "Smith" // setter access
        println("new person: ${p.name} (${if (p.married) "" else "not "}married)") // getter access
        println("full name: ${p.fullname}")
        
    }


}