package tutorial

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

/**
 * Collections: https://kotlinlang.org/docs/reference/collections.html
 *
 * @author Lukasz Frankowski
 */
@Suppress("ReplacePutWithAssignment", "UNUSED_VARIABLE", "UNUSED_VALUE", "UNUSED_DESTRUCTURED_PARAMETER_ENTRY")
fun main(args: Array<String>) {

    println("array")
    run {
        val array: Array<Int> = arrayOf(1, 2, 3) // array has fixed elements size
        array[0] = 4 // and is mutable
        println(array.joinToString(", ")) // 4, 2, 3

        val intArray = intArrayOf(1, 2, 3) // there are specialized array types for primitives which are optimized with non-boxed items:
        // IntArray, ByteArray, ShortArray, CharArray
        println(intArray.joinToString(", ")) // 1, 2, 3

    }

    println("\nlist")
    run {
        var list: MutableList<Int> = mutableListOf(1, 2, 3) // list has variable elements size, this is array list equivalent
        list.add(4) // is mutable
        println(list.joinToString(", ")) // 1, 2, 3, 4

        val readonlyList: List<Int> = list // this is readonly view of "list", because List<*> interface is immutable
        // readonlyList.add(5) // Error:(29, 22) Kotlin: Unresolved reference: add

        list = LinkedList() // example creation of different kind of list
    }

    println("\nmap")
    run {
        var map: MutableMap<String, Int> = mutableMapOf("one" to 1, "two" to 2, "three" to 3) // linked hash map equivalent
        map.put("four", 4) // is mutable
        map["five"] = 5 // you can also use overloaded [] operator
        println(map.entries.joinToString(", ")) // one=1, two=2, three=3, four=4, five=5

        val readonlyMap: Map<String, Int> = map // this is readonly view of "map", because Map<*> interface is immutable
        // readonlyMap.put("six", 6) // Error:(45, 21) Kotlin: Unresolved reference: put

        map = HashMap() // example creation of different kind of map
    }

    println("\nsequences")
    run {
        // sequences are equivalent of java streams

        val map = mutableMapOf("one" to 1, "two" to 2, "three" to 3, "four" to 4, "five" to 5)
        val list = map.asSequence()
            .filter { (k, v) -> k.startsWith("t") || v > 4 }
            .map { (k, v) -> k.toUpperCase() }
            .toCollection(ArrayList())
        println(list.joinToString(", ")) // TWO, THREE, FIVE
    }
}