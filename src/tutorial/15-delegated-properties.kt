package tutorial

import kotlin.properties.Delegates
import kotlin.reflect.KProperty

/**
 * Delegated Properties: https://kotlinlang.org/docs/reference/delegated-properties.html
 *
 * @author Lukasz Frankowski
 */
fun main(args: Array<String>) {

    println("property delegation basics")
    run {
        // this class will be delegate of property
        class Delegate<T>(var x: T) {
        
            operator fun getValue(thisRef: Any?, property: KProperty<*>): T { // accessing value method pattern
                println("getting value by delegate (${thisRef}, ${property.name})")
                return x
            }

            operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) { // setting value method pattern
                println("setting value by delegate (${thisRef}, ${property.name})")
                x = value
            }
        }

        class Delegated {
            var x: Int by Delegate(1) // this property is delegated to Delegate class using "by" operator
        }

        val delegated = Delegated()
        delegated.x = 2 // setting value by delegate (1$Delegated, x)
        println(delegated.x) // getting value by delegate (1$Delegated, x) ... 2

        // also local variables can be delegated
        var x: Int by Delegate(1)
        x = 2 // setting value by delegate (null, x)
        println(x) // getting value by delegate (null, x) ... 2
    }

    println("\ndefault lazy delegate")
    run {
        // lazy is a function which gets initialization lambda and returns Lazy<T> delegate
        val lazyValue: String by lazy {
            println("initializing lazy value")
            "hello" // which can be used to lazily initialize property value
        }

        println(lazyValue) // initializing lazy value ... hello
        println(lazyValue) // hello
    }

    println("\ndefault observable delegate")
    run {
        // observable is a function which gets default value and observable lambda, and returns property delegate
        var observableValue: Int by Delegates.observable(1) {
            prop, old, new ->
            println("changing ${prop.name} from $old to $new") // which can be used to observe changes on the property
        }
        println(observableValue) // 1
        observableValue = 2 // changing observableValue from 1 to 2
        println(observableValue) // 2
    }

    println("\ndefault map delegate")
    run {
        // you can also delegate properties to be kept in map
        class MapPropertiesClass(val map: MutableMap<String, Any>) {
            var name: String by map
        }

        val o = MapPropertiesClass(HashMap(mapOf("name" to "Tom")))
        println(o.name) // Tom
        o.name = "Joe"
        println(o.name) // Joe
        println(o.map) // name=Joe
    }
    
}