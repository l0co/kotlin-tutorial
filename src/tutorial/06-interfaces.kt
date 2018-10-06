package tutorial

import java.time.LocalDate

/**
 * Interfaces: https://kotlinlang.org/docs/reference/interfaces.html
 *
 * @author Lukasz Frankowski
 */

// NOTE: interfaces cannot be declared on non-top level block, so we put all of them here

interface Aged {
    fun calculateAge(): Int

    fun calculateYearOfAppearance(): Int {
        return LocalDate.now().year - calculateAge()
    }
}

interface Named {
    val name: String
}

interface Vehicle: Named {
    fun stop()
    fun start() {
        println("starting $name vehicle")
    }
}

interface Device: Named {
    fun stop()
    fun start() {
        println("starting $name device")
    }
}

fun main(args: Array<String>) {

    println("implementing interface")
    run {
        // abstract interface methods need to be defined here, while default interface methods stays on interface
        class OldThing: Aged { // ":" stands for both: "extends" and "implements"

            override fun calculateAge(): Int {
                return 10
            }

        }
        val oldThing = OldThing()
        println("old thing age is ${oldThing.calculateAge()} and is appeared in ${oldThing.calculateYearOfAppearance()} year") // old thing age is 10 and is appeared in 2008 year

        // abstract classes don't have to implement interface methods, same as in java
        abstract class AbstractOldThing: Aged 
    }

    println("\nimplementing properties in interfaces")
    run {

        // if interface declares properties, they need to be implemented in the implementing class

        class Person(override val name: String) : Named { // by implicit constructor property with "overrride"
        }

        class Person2: Named {
            override val name: String = "Tom" // or by declared property
        }
    }

    println("\nimplementing multiple interfaces")
    run {
        open class NamedThing protected constructor(override val name: String) : Named

        class Digger: NamedThing("digger"), Vehicle, Device {

            override fun stop() { // needs to be implemented for both interfaces
                println("stopping $name")
            }

            override fun start() { // this is already implemented in interfaces, but name conflicts, so we are forced by compiler to reimplement
                super<Vehicle>.start() // we can call super interface methods with qualifier
                super<Device>.start()
            }

        }

        with (Digger()) {
            start() // starting digger vehicle, starting digger device
            stop() // stopping digger
        }
    }

}

