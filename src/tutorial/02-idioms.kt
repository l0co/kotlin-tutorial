package tutorial

/**
 * Idioms: https://kotlinlang.org/docs/reference/idioms.html
 *
 * @author Lukasz Frankowski
 */
@Suppress("ReplaceCallWithBinaryOperator")
fun main(args: Array<String>) {

    println("data classes (DTO, POJO ...)")
    run {
        // default class
        class Customer1(val name: String, var married: Boolean) // default public properties (val=getter only, var=getter/setter)
        val c1 = Customer1("Tom", true)
        c1.married = false; // divorce
        // c1.name = "Alice"; // can't do it, readonly
        println(c1) // tom customer: tutorial._02_idiomsKt$main$1$Customer1@6b71769e: no default toString()
        println(c1.equals(Customer1("Tom", false))) // false: no default equals()

        // data class has toString, equals (on all props), hashCode, copy
        data class Customer2(val name: String, var married: Boolean)
        val d1 = Customer2("Tom", true)
        println(d1) // Customer2(name=Tom, married=true): default toString()
        println(d1.equals(Customer2("Tom", true))) // true: default equals()
    }

    println("\ndefault values for functions")
    run {
        fun runme(a: Int = 1, b: Int = 2) {
            println("$a, $b")
        }

        runme() // 1, 2
        runme(b = 3) // 1, 3: example of changing only one of default arguments by giving its name
    }

    println("\nreadonly list & map")
    run {
        val list = listOf("a", "b", "c")
        for (i in list)
            println(i) // a, b, c

        val map = mapOf("a" to "A", "b" to "B", "c" to "C")
        for (i in map)
            println(i) // a=A, b=B, c=C: i = Map.Entry
        for ((k, v) in map) // key-value iteration using destructuring
            println("$k => $v") // a => A, b => B, c => C
    }

    println("\nlazy property")
    run {
        val s: String by lazy {
            println("computing s...")
            "a"
        }
        println(s) // computing s... a
        println(s) // a
    }

    println("\nextending existing class with extension function")
    run {
        fun String.getA(): String {
            return "A";
        }
        println("mystring".getA()) // A
    }

    println("\nsingleton with 'object' keyword")
    run {
        val s = object {
            val name = "local singleton"
        }
        println(s.name) // local singleton
        println(Singleton.name) // declared singleton - see declaration at the end of this file (cannot be declared in block)
    }

    println("\nnot null shorthand with ?")
    run {
        val list: List<String>? = null; // ? = nullable property
        println(list?.size) // null: whole expression returns null, and ? is enforced by ? in declaration
        println(list?.size ?: "list is not initialized") // list is not initialized: ?: = if null

        // usage of Object.let to execute block if the value is not null
        list?.let {
            println("list is not null and here it is: ${it}") // not executed
        }
    }

    println("\ntry..catch as expression")
    run {
        val result = try {
            "1"
        } catch (e: Exception) {
            println("error!")
        }
        println(result) // 1
    }

    println("\nwith to operate on object instance")
    run {
        with ("the string") {
            println(length) // 10
        }
    }

}

object Singleton {
    val name = "declared singleton"
}
