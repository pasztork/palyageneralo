package com.pasztork.image

import java.awt.image.BufferedImage

/**
 * Creates pixel array from one of the image's rows.
 */
fun BufferedImage.getRowAsPixelArray(rowIndex: Int): PixelArray<Int> =
    IntPixelArray(Array(this.width) { getRGB(it % this.width, rowIndex) })

/**
 * Creates pixel array from one of the image's columns.
 */
fun BufferedImage.getColumnAsPixelArray(columnIndex: Int): PixelArray<Int> =
    IntPixelArray(Array(this.height) { getRGB(columnIndex, it % this.height) })

/**
 * Creates an array of integers from a BufferedImage.
 */
fun BufferedImage.getPixelValuesAsArray(): IntArray {
    val pixels = IntArray(width * height)
    pixels.forEachIndexed { index, _ ->
        val x = index / width
        val y = index % height
        pixels[index] = getRGB(x, y)
    }
    return pixels
}

/**
 * Rotates a BufferedImage by 90 degrees to the right and returns it.
 */
fun BufferedImage.rotateRight(): BufferedImage {
    val destination = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
    val graphics = destination.createGraphics()
    graphics.rotate(
        Math.toRadians(90.0), width.toDouble() / 2, height.toDouble() / 2
    )
    graphics.drawImage(this, null, 0, 0)
    return destination
}