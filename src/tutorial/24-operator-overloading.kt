package tutorial

/**
 * Operator Overloading: https://kotlinlang.org/docs/reference/operator-overloading.html
 *
 * @author Lukasz Frankowski
 */
@Suppress("EqualsOrHashCode")
fun main(args: Array<String>) {

    class Point(var x: Int, var y: Int): Comparable<Point> {

        operator fun unaryMinus() = Point(-x, -y)
        operator fun unaryPlus() = Point(Math.abs(x), Math.abs(y))
        operator fun not() = Point(Int.MAX_VALUE, Int.MAX_VALUE)
        operator fun inc() = Point(x+1, y+1)
        operator fun dec() = Point(x-1, y-1)
        operator fun plus(point: Point) = Point(x+point.x, y+point.y)
        operator fun minus(point: Point) = Point(x-point.x, y-point.y)
        operator fun times(point: Point) = Point(x*point.x, y*point.y)
        operator fun div(point: Point) = Point(x/point.x, y/point.y)
        operator fun rem(point: Point) = Point(x%point.x, y%point.y)

        operator fun rangeTo(point: Point) = object: ClosedRange<Point>, Iterable<Point> {
            override val endInclusive: Point
                get() = point
            override val start: Point
                get() = this@Point

            override fun iterator(): Iterator<Point> = object: Iterator<Point> {
                lateinit var currentPoint: Point
                var next = true

                override fun hasNext(): Boolean {
                    if (!next)
                        return next

                    if (!::currentPoint.isInitialized)
                        currentPoint = start;
                    else
                        currentPoint++;

                    if (currentPoint > endInclusive)
                        next = false

                    return next
                }

                override fun next(): Point {

                    return if (next) currentPoint else throw NoSuchElementException()
                }

            }
        }

        override fun compareTo(other: Point): Int = x.compareTo(other.x) + y.compareTo(other.y)
        override fun equals(other: Any?): Boolean {
            return other is Point && x==other.x && y==other.y
        }

        override fun toString(): String {
            return "Point($x, $y)"
        }

        operator fun invoke() {
            println(this)
        }

    }

    class Path {
        protected val _points = mutableListOf<Point>()

        val points: List<Point>
            get() = _points

        fun add(p: Point) {
            _points.add(p)
        }

        operator fun get(i: Int): Point {
            return _points[i]
        }

        operator fun set(i: Int, p: Point) {
            _points[i] = p
        }

        operator fun contains(p: Point): Boolean {
            _points.forEach { if (it == p) return true }
            return false
        }

    }

    var p1 = Point(10,20)

    // NOTE: following arithmetic operators are immutable (doesn't influence source Point object)

    println(p1) // Point(10, 20)
    println(-p1) // Point(-10, -20): unaryMinus operator
    println(+-p1) // Point(10, 20): unaryPlus + unaryMinus operator
    println(!p1) // Point(2147483647, 2147483647): not operator
    println(++p1) // Point(11, 21): inc operator (NOTE: this operator replaces p1 reference with new Point)
    println(--p1) // Point(10, 20): dec operator (NOTE: this operator replaces p1 reference with new Point)
    println(p1 + Point(2, 2)) // Point(12, 22): plus operator
    println(p1 - Point(2, 2)) // Point(8, 18): minus operator
    println(p1 * Point(2, 2)) // Point(20, 40): times operator
    println(p1 / Point(2, 2)) // Point(5, 10): div operator
    println(p1 % Point(3, 3)) // Point(1, 2): rem operator (modulo)

    // other operators

    for (p in Point(1, 1)..Point(3, 3)) // uses interable rangeTo operator
        println(p) // Point(1, 1) ... Point(2, 2) ... Point(3, 3)

    val path = Path()
    path.add(Point(1, 1))
    path.add(Point(2, 2))
    println(path[0]) // Point(1, 1): get operator
    println(path[1]) // Point(2, 2): get operator
    path[1] = Point(3, 3) // set operator
    println(path[1]) // Point(3, 3): get operator

    println(Point(1, 1) in path) // true: contains operator
    println(Point(2, 2) !in path) // true: contains operator

    println(Point(1, 1) == Point(1, 1)) // true: equals operator
    println(Point(1, 1) == Point(1, 2)) // false: equals operator
    println(Point(1, 1) < Point(1, 2)) // true: compareTo operator

    p1() // prints Point(10, 20): invoke operator
    
}