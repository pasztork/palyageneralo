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