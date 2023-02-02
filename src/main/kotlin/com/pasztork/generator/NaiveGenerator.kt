package com.pasztork.generator

import com.pasztork.image.NullPixelArray
import com.pasztork.image.PixelArray
import com.pasztork.image.getColumnAsPixelArray
import com.pasztork.image.getRowAsPixelArray
import java.awt.image.BufferedImage

class NaiveGenerator() : GeneratorBase() {
    override fun generateImage(gridSystem: GridSystem) {
        if (gridSystem.isFull) {
            return
        }
        fillNextEmptyField(gridSystem)
        generateImage(gridSystem)
    }

    private fun fillNextEmptyField(gridSystem: GridSystem) {
        val position = gridSystem.nextEmptyField

        val neighborTop: PixelArray = if (position.row == 0) {
            NullPixelArray()
        } else {
            gridSystem[position.row - 1, position.column]!!.getRowAsPixelArray(
                subImages[0].height - 1
            )
        }

        val neighborLeft: PixelArray = if (position.column == 0) {
            NullPixelArray()
        } else {
            gridSystem[position.row, position.column - 1]!!.getColumnAsPixelArray(
                subImages[0].width - 1
            )
        }

        gridSystem[position.row, position.column] =
            findMatchingSubImage(neighborTop, neighborLeft)
    }

    private fun findMatchingSubImage(
        neighborTop: PixelArray, neighborLeft: PixelArray
    ): BufferedImage {
        val possibleImages = mutableListOf<BufferedImage>()
        subImages.forEach {
            val topArray = it.getRowAsPixelArray(0)
            val leftArray = it.getColumnAsPixelArray(0)
            if (neighborTop.matches(topArray) && neighborLeft.matches(leftArray)) {
                possibleImages.add(it)
            }
        }
        return possibleImages[(0 until possibleImages.size).random()]
    }
}