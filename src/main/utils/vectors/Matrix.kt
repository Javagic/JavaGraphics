package main.utils.vectors

class Matrix() {
    lateinit var list: ArrayList<ArrayList<Double>>


    init {
        list = ArrayList()
        for (i in 0 until 3) {
            list.add(ArrayList())
            for (j in 0 until 3) {
                list[i].add(0.0)
            }
        }
    }

    fun T(): Matrix {
        val matrix = Matrix()
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                matrix.list[i][j] = list[j][i]
            }
        }
        return matrix
    }
}