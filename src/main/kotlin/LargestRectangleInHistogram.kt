import kotlin.math.max
import kotlin.math.min

object LargestRectangleInHistogram {

    //list size >= 1 and <= 100000
    //For every a[i] =>    1 <= a[i] <= 1000000000
    //So we know it can overflow but the problem requests an int,
    // so I'll assume it can't overflow by construction of the input
    fun solve(a: List<Int>): Int {
        var maxArea = max(a[0], a.size)

        for (start in 0..a.lastIndex) {
            var minCurSection = a[start]
            for (i in start..a.lastIndex) {
                minCurSection = min(a[i], minCurSection)
                if (minCurSection * (a.lastIndex - start + 1) <= maxArea) break
                maxArea = max(maxArea, (i - start + 1) * minCurSection)
            }
        }
        return maxArea
    }
}