package com.pasztork.generator

import com.pasztork.image.NullPixelArray
import com.pasztork.image.PixelArray
import com.pasztork.image.getColumnAsPixelArray
import com.pasztork.image.getRowAsPixelArray
import java.awt.image.BufferedImage

/**
 * My naive implementation of map generation.
 */
class NaiveGenerator() : GeneratorBase() {

    /**
     * Generator function.
     */
    override fun generateImage(gridSystem: GridSystem) {
        if (gridSystem.isFull) {
            return
        }
        fillNextEmptyField(gridSystem)
        generateImage(gridSystem)
    }

    /**
     * Fills the very next empty field in the grid.
     */
    private fun fillNextEmptyField(gridSystem: GridSystem) {
        val position = gridSystem.nextEmptyField

        val neighborTop = if (position.row == 0) {
            NullPixelArray()
        } else {
            gridSystem[position.row - 1, position.column]!!.getRowAsPixelArray(
                subImages[0].height - 1
            )
        }

        val neighborLeft = if (position.column == 0) {
            NullPixelArray()
        } else {
            gridSystem[position.row, position.column - 1]!!.getColumnAsPixelArray(
                subImages[0].width - 1
            )
        }

        gridSystem[position.row, position.column] =
            findMatchingSubImage(neighborTop, neighborLeft)
    }

    /**
     * Returns an image that matches the current grid system.
     */
    private fun findMatchingSubImage(
        neighborTop: PixelArray<Int>, neighborLeft: PixelArray<Int>
    ): BufferedImage {
        val possibleImages = mutableListOf<BufferedImage>()
        subImages.forEach {
            val topArray = it.getRowAsPixelArray(0)
            val leftArray = it.getColumnAsPixelArray(0)
            if (neighborTop == topArray && neighborLeft == leftArray) {
                possibleImages.add(it)
            }
        }
        return possibleImages[(0 until possibleImages.size).random()]
    }
}