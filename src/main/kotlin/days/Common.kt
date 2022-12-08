package days

fun neighborsAndSelf(x: Int, y: Int) =
    (x..y).flatMap { dy ->
        (x..y).map { dx ->
            Pair(dx, dy)
        }
    }
