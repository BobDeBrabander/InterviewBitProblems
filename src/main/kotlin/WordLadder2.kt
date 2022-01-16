object WordLadder2 {

    data class DistNode(val value: String) {
        var dist = Integer.MAX_VALUE
        val adj = mutableListOf<DistNode>()
    }

    fun findLadders(start: String, end: String, dict: List<String>): List<List<String>> {
        if (start == end) return listOf(listOf(start))
        val nodes = dict.map { DistNode(it) }
        val nodeStart = DistNode(start)
        nodeStart.dist = 0
        val nodeEnd = DistNode(end)
        if (oneDistAway(nodeStart.value, nodeEnd.value)) connectNodes(nodeStart, nodeEnd)
        for (i in nodes.indices) {
            val n1 = nodes[i]
            if (oneDistAway(start, n1.value)) connectNodes(nodeStart, n1)
            if (oneDistAway(end, n1.value)) connectNodes(nodeEnd, n1)
            for (j in i + 1..nodes.lastIndex) {
                val n2 = nodes[j]
                if (oneDistAway(n1.value, n2.value)) connectNodes(n1, n2)
            }
        }
        bfs(nodeStart)
        val solutionList = mutableSetOf<List<String>>()
        constructSolutions(nodeEnd, mutableListOf(), solutionList)
        return solutionList.toList()
    }

    private fun bfs(startNode: DistNode) {
        val q = ArrayDeque<DistNode>()
        q.add(startNode)
        while (q.isNotEmpty()) {
            val curNode = q.removeFirst()
            curNode.adj.forEach {
                if (it.dist > curNode.dist + 1) {
                    it.dist = curNode.dist + 1
                    q.add(it)
                }
            }
        }
    }

    private fun constructSolutions(
        endNode: DistNode,
        curSolution: MutableList<String>,
        solutionSet: MutableSet<List<String>>
    ) {
        curSolution.add(endNode.value)
        if (endNode.dist == 0) {
            solutionSet.add(curSolution.toList().reversed()) //first copy and then reverse as reverse is in place
        } else {
            endNode.adj.forEach {
                if (it.dist + 1 == endNode.dist) {
                    constructSolutions(it, curSolution, solutionSet)
                }
            }
        }
        curSolution.removeLast()
    }

    private fun oneDistAway(s1: String, s2: String): Boolean {
        //All words have the same length, so the only one diff can be a substitution
        var diff = 0
        for (i in s1.indices) {
            if (s1[i] != s2[i]) {
                diff++
                if (diff > 1) return false
            }
        }
        return true
    }

    private fun connectNodes(n1: DistNode, n2: DistNode) {
        n1.adj.add(n2)
        n2.adj.add(n1)
    }
}