package tutorial

@Suppress("UNUSED_VARIABLE", "SENSELESS_COMPARISON", "UNREACHABLE_CODE", "ReplaceSingleLineLet", "CanBeVal", "CAST_NEVER_SUCCEEDS")
/**
 * Null Safety: https://kotlinlang.org/docs/reference/null-safety.html
 *
 * @author Lukasz Frankowski
 */
fun main(args: Array<String>) {

    println("null safety basics")
    run {
        // null cannot be assigned to variable by default
        val notNullable: String
        // notNullable = null // Error:(13, 23) Kotlin: Null can not be a value of a non-null type String

        // it can only be assigned with explicit ? type declaration
        val nullable: String? // ? = nullable
        nullable = null

        // when variable is nullable the compiler always reminds about this
        // println(nullable.length) // Error:(21, 25) Kotlin: Only safe (?.) or non-null asserted (!!.) calls are allowed on a nullable receiver of type String?

        // you need to add null check with another ? operator
        println(nullable?.length) // null: if the object is null, whole expression will be null
        if (nullable!=null) // or you can access nullable object in a classic way
            println(nullable.length) // not executed

        // to execute code when object is not null you can use ? safety operator with "let" extension
        nullable?.let {
            println("A") // not executed
        }
    }

    println("\n!! operator")
    run {
        // !! operator forces variable to be treated as not null
        val nullable: String? = null

        try {
            println(nullable!!.length) // throws exception
        } catch (e: Exception) {
            println("${e::class.simpleName}: ${e.message}") // KotlinNullPointerException: null
        }
    }

    println("\nsafe cast for null")
    run {
        val s: String = "text"

        // if we don't know the source object type and we want to cast it, we may end up with class cast exception
        try {
            val i: Int = s as Int // throws exception
        } catch (e: Exception) {
            println("${e::class.simpleName}: ${e.message}") // ClassCastException: java.lang.String cannot be cast to java.lang.Integer
        }

        // with ? operator we can make it safe
        val i: Int? = s as? Int // as? is safe case, but "i" needs to be nullable
        println(i) // no exception, but "i" is null in this scenario
    }
}