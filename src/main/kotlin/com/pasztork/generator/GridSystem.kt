package com.pasztork.generator

import com.pasztork.data.Size
import java.awt.image.BufferedImage

class GridSystem(private val size: Size<Int>) {
    private val grid = arrayOfNulls<BufferedImage>(size.x * size.y)

    val isFull: Boolean
        get() = grid.none { it == null }

    val bufferedImage: BufferedImage
        get() {
            val image = BufferedImage(
                size.x * grid[0]!!.width,
                size.y * grid[0]!!.height,
                BufferedImage.TYPE_INT_ARGB
            )
            grid.forEachIndexed { index, bufferedImage ->
                for (row in 0 until bufferedImage!!.height) {
                    for (column in 0 until bufferedImage.width) {
                        val x = column + (index % size.x) * bufferedImage.width
                        val y = row + (index / size.x) * bufferedImage.height
                        image.setRGB(x, y, bufferedImage.getRGB(column, row))
                    }
                }
            }
            return image
        }

    val nextEmptyField: GridCoordinate
        get() {
            for (row in 0 until size.y) {
                for (column in 0 until size.x) {
                    if (grid[size.x * row + column] == null) {
                        return GridCoordinate(row, column)
                    }
                }
            }
            error("No empty field found")
        }

    operator fun get(row: Int, column: Int) = grid[size.x * row + column]

    operator fun set(row: Int, column: Int, bufferedImage: BufferedImage) {
        grid[size.x * row + column] = bufferedImage
    }
}
