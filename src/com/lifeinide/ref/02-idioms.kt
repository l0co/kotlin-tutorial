package com.lifeinide.ref

@Suppress("ReplaceCallWithBinaryOperator")
fun main(args: Array<String>) {

    println("data classes (DTO, POJO ...)")
    run {
        class Customer1(val name: String, var married: Boolean) // default public properties (val=getter only, var=getter/setter)
        val c1 = Customer1("Tom", true)
        c1.married = false; // divorce
        // c1.name = "Alice"; // can't do it
        println("tom customer: $c1")
        println("tom equals tom? ${c1.equals(Customer1("Tom", false))}")

        // data class has toString, equals (on all props), hashCode, copy
        data class Customer2(val name: String, var married: Boolean) // default public properties (val=getter only, var=getter/setter)
        val d1 = Customer2("Tom", true)
        println("tom customer 2: $d1")
        println("tom equals tom? ${d1.equals(Customer2("Tom", true))}")
    }

    println("\ndefault values for functions")
    run {
        fun runme(a: Int = 1) {
            println(a)
        }
        runme()
    }

    println("\nreadonly list & map")
    run {
        val list = listOf("a", "b", "c")
        for (i in list)
            println(i)

        val map = mapOf("a" to "A", "b" to "B", "c" to "C")
        for (i in map)
            println(i) // i = Map.Entry
        for ((k, v) in map) // key-value iteration
            println("$k => $v") 
    }

    println("\nlazy property")
    run {
        val s: String by lazy {
            println("computing s...")
            "a"
        }
        println(s)
    }

    println("\nextending existing class with extension function")
    run {
        fun String.getA(): String {
            return "A";
        }
        println("mystring".getA())
    }

    println("\nsingleton with 'object' keyword")
    run {
        val s = object {
            val name = "Local singleton"
        }
        println(s.name)
        println(Singleton.name)
    }

    println("\nnot null shorthand with ?")
    run {
        val list: List<String>? = null;
        println(list?.size) // null: whole expression returns null, and ? is enforced by ? in declaration
        println(list?.size ?: "list is not initialized") // list is not initialized: ?: = if null

        // usage of Object.let to execute block if the value is not null
        list?.let {
            println("list is not null and here it is: ${it}")
        }
    }

    println("\ntry..catch as expression")
    run {
        val result = try {
            "1"
        } catch (e: Exception) {
            println("error!")
        }
        println(result)
    }

    println("\nwith to operate on object instance")
    run {
        with ("the string") {
            println(length)
        }
    }

}

object Singleton {
    val name = "Declared singleton"
}
