package tutorial

/**
 * Enum Classes: https://kotlinlang.org/docs/reference/enum-classes.html
 *
 * @author Lukasz Frankowski
 */

// enum classes are basically the same as in java

// basic enum
private enum class Status {
    OPENED,
    CLOSED
}

// extended enum
enum class ColoredStatus(val color: Int): Greetable { // can have constructor and implement interfaces

    OPENED(1) {

        override fun hello() { // interface method implementation
            println("hello, ${sysName()}")
        }

        override fun sysName(): String { // internal method implementation
            return "opened"
        }
    };

    protected abstract fun sysName(): String // can have internal methods
    
}

