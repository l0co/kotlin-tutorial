package tutorial

/**
 * Type-Safe Builders: https://kotlinlang.org/docs/reference/type-safe-builders.html
 *
 * @author Lukasz Frankowski
 */
fun main(args: Array<String>) {

    println("type safe builder example") // uses function literals with receiver - see 17-lambdas.kt

    // we will build tree of Node-s with type-safe builder expression

    class Node(val name: String) {

        val children = mutableListOf<Node>()

        fun node(name: String, init: Node.() -> Unit): Node {
            val node = Node(name)
            node.init()
            children.add(node)
            return node
        }

        override fun toString(): String {
            return "$name(${children.joinToString(", ")})"
        }
    }

    fun rootNode(name: String, init: Node.() -> Unit): Node {
        val node = Node(name)
        node.init()
        return node
    }

    // DLS-like definition of tree
    val n = rootNode("root") {

        node("A") {}
        node("B") {
            node("C") {}
            node("D") {}
        }

    }

    println(n) // root(A(), B(C(), D()))

}